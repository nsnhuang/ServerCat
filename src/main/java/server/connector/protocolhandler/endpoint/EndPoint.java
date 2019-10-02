package server.connector.protocolhandler.endpoint;

import server.connector.protocolhandler.endpoint.poller.wrapper.Wrapper;
import server.lifecycle.Lifecycle;
import server.lifecycle.LifecycleState;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @author huang
 */
public interface EndPoint extends Lifecycle {

    void execute(Wrapper attachment);

    /**
     * 返回端口
     */
    int getPort();

    @Override
    LifecycleState getState();
}
