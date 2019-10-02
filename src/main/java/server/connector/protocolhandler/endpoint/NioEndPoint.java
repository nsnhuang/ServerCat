package server.connector.protocolhandler.endpoint;

import lombok.extern.slf4j.Slf4j;
import server.connector.protocolhandler.endpoint.poller.Poller;
import server.connector.protocolhandler.endpoint.poller.wrapper.Wrapper;
import server.connector.protocolhandler.processor.NioProcessor;
import server.lifecycle.LifecycleState;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-22 1:30 PM
 */
@Slf4j
public class NioEndPoint extends AbstractEndPoint {

    private Acceptor acceptor;
    private int pollerCount = Math.min(2, Runtime.getRuntime().availableProcessors());
    private AtomicInteger pollerRotate = new AtomicInteger(0);
    private Poller[] pollers;
    private Executor pool;
    private ServerSocketChannel ssChannel;

    public NioEndPoint() {
        this(8080);
    }

    public NioEndPoint(int port) {
        this.port = port;
    }

    @Override
    public void execute(Wrapper attachment) {
        pool.execute(new NioProcessor(attachment));
    }

    public void registerToPoller(SocketChannel client) {
        new Thread(() -> getPoller().register(client)).start();
    }

    private Poller getPoller() {
        int id = Math.abs(pollerRotate.incrementAndGet() % pollerCount);
        return pollers[id];
    }

    @Override
    protected void initInternal() {

        pool = new ThreadPoolExecutor(
                100,
                120,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),
                new ThreadFactory() {
                    private int count;

                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "workerThread-" + count++);
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            this.ssChannel = ServerSocketChannel.open();
            ssChannel.bind(new InetSocketAddress(port));
            ssChannel.configureBlocking(true);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("ssChannel IOException");
        }

    }

    @Override
    protected void startInternal() {
        initAcceptor();
        initPoller();
    }

    @Override
    protected void stopInternal() {

    }

    /**
     * 初始化Acceptor
     */
    private void initAcceptor() {
        String acceptorName = "NIOAcceptor";
        acceptor = new Acceptor(acceptorName);
        Thread acceptorThread = new Thread(acceptor, acceptorName);
        acceptorThread.setDaemon(true);
        acceptorThread.start();
    }

    /**
     * 初始化Poller
     */
    private void initPoller() {
        pollers = new Poller[pollerCount];
        for (int i = 0; i < pollerCount; i++) {
            String pollerName = "NIOPoller-" + i;
            Poller nioPoller = null;
            try {
                nioPoller = new Poller(this, pollerName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread pollerThread = new Thread(nioPoller, pollerName);
            pollerThread.setDaemon(true);
            pollerThread.start();
            pollers[i] = nioPoller;
        }
    }

    class Acceptor implements Runnable {

        private String name;

        public Acceptor(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            log.info(Thread.currentThread().getName() + "[Acceptor:" + name + "]开始监听, 端口" + NioEndPoint.this.getPort());
            while (getState() == LifecycleState.STARTED || getState() == LifecycleState.STARTING_PREP) {
                SocketChannel client;
                try {
                    client = ssChannel.accept();
                    log.info("Acceptor[" + name + "]接到请求...[" + client + "]");
                    NioEndPoint.this.registerToPoller(client);
                } catch (IOException e) {
                    log.error("Acceptor[" + name + "] IOException");
                }
            }
        }
    }


}