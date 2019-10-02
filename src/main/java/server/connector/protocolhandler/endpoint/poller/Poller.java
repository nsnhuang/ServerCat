package server.connector.protocolhandler.endpoint.poller;

import server.connector.protocolhandler.endpoint.EndPoint;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import server.connector.protocolhandler.endpoint.poller.wrapper.NioSocketWrapper;
import server.connector.protocolhandler.endpoint.poller.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import server.lifecycle.AbstractLifecycle;
import server.lifecycle.LifecycleState;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-23 9:40 AM
 */
@Slf4j
public class Poller implements Runnable{

    private EndPoint server;
    private String name;
    private Queue<PollerEvent> events;
    private Selector selector;

    private final Lock LOCK = new ReentrantLock();
    private final Condition NOT_EMPTY = LOCK.newCondition();

    public Poller(EndPoint endPoint, String name) throws IOException {
        this.server = endPoint;
        this.name = name;
        events = new ConcurrentLinkedQueue<>();
        selector = Selector.open();
    }

    public void register(SocketChannel client) {
        LOCK.lock();
        Wrapper wrapper = new NioSocketWrapper(server, client, this);
        events.offer(new PollerEvent(wrapper));
        NOT_EMPTY.signalAll();
        LOCK.unlock();
    }

    @Override
    public void run() {
        log.info("Poller[" + name + "] 等待连接");

        while (server.getState() == LifecycleState.STARTING_PREP || server.getState() == LifecycleState.STARTED) {
            LOCK.lock();
            try {
                while (events.size() == 0) {
                    NOT_EMPTY.await();
                }
                registerEvent();
                log.info("PollerEvent[" + name + "]注册完毕");
                selector.select();
                Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();

                while (selectionKeyIterator.hasNext()) {
                    SelectionKey key = selectionKeyIterator.next();
                    if (key.isReadable()) {
                        Wrapper attachment = (Wrapper) key.attachment();
                        if (attachment != null) {
                            processSocket(attachment);
                        }
                    }
                    selectionKeyIterator.remove();
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        }
    }


    private void processSocket(Wrapper attachment) {
        server.execute(attachment);
    }

    private void registerEvent() {
        if (events.size() != 0) {
            for (int i = 0; i < events.size(); i++) {
                events.poll().run();
            }
        }
    }

    public Selector getSelector() {
        return selector;
    }
}