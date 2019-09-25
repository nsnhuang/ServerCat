package connector.endpoint;

import connector.endpoint.acceptor.Acceptor;
import connector.endpoint.poller.Poller;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

import connector.endpoint.dispatcher.Dispatcher;
import connector.endpoint.dispatcher.NioDispatcher;
import connector.endpoint.wrapper.Wrapper;
import container.Container;
import container.DefaultContainer;
import lombok.extern.slf4j.Slf4j;
/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-22 2:14 PM
 */
@Slf4j
public abstract class AbstractEndPoint implements EndPoint {

    protected Acceptor acceptor;
    protected volatile boolean isRunning = true;
    protected int pollerCount = Math.min(2, Runtime.getRuntime().availableProcessors());
    protected Poller[] pollers;
    protected AtomicInteger pollerRotate = new AtomicInteger(0);
    protected int port;
    protected ServerSocketChannel ssChannel;
    protected Dispatcher dispatcher;
    protected Container context;

    @Override
    public void execute(Wrapper attachment) {
        dispatcher.doDispatcher(attachment);
    }

    @Override
    public void registerToPoller(SocketChannel client) {
        new Thread(() -> getPoller().register(client, true)).start();
    }

    private Poller getPoller() {
        int id = Math.abs(pollerRotate.incrementAndGet() % pollerCount);
        return pollers[id];
    }

    @Override
    public void start() {
        try {
            initAcceptor();
            initPoller();
            initDispatcher();
            initContainer();
            log.info("服务器启动");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initContainer() {
        context = new DefaultContainer();
        context.setName("context-1");
        Container wrapper = new DefaultContainer();
        wrapper.setName("wrapper-1");

        context.addChild(wrapper);
        wrapper.setParent(context);

    }

    private void initDispatcher() {
        dispatcher = new NioDispatcher();
    }

    /**
     * 初始化Poller
     */
    private void initPoller() throws IOException {
        pollers = new Poller[pollerCount];
        for (int i = 0; i < pollerCount; i++) {
            String pollerName = "NIOPoller-" + i;
            Poller nioPoller = new Poller(this, pollerName);
            Thread pollerThread = new Thread(nioPoller, pollerName);
            pollerThread.setDaemon(true);
            pollerThread.start();
            pollers[i] = nioPoller;
        }
    }

    /**
     * 初始化Acceptor
     */
    private void initAcceptor() {
        String acceptorName = "NIOAcceptor";
        acceptor = new Acceptor(this, acceptorName);
        Thread acceptorThread = new Thread(acceptor, acceptorName);
        acceptorThread.setDaemon(true);
        acceptorThread.start();
    }

    @Override
    public void close() {

    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public SocketChannel accept() throws IOException {
        return ssChannel.accept();
    }

    @Override
    public Container getContext() {
        return context;
    }
}