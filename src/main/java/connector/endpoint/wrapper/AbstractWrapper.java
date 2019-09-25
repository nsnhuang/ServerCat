package connector.endpoint.wrapper;

import connector.endpoint.poller.Poller;
import connector.endpoint.EndPoint;

import java.nio.channels.SocketChannel;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-23 1:28 PM
 */
public abstract class AbstractWrapper implements Wrapper {

    protected EndPoint server;
    protected SocketChannel client;
    protected Poller poller;

    @Override
    public EndPoint getServer() {
        return server;
    }

    @Override
    public SocketChannel getClient() {
        return client;
    }

    @Override
    public Poller getPoller() {
        return poller;
    }
}