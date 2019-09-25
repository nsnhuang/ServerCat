package connector.endpoint.wrapper;

import connector.endpoint.poller.Poller;
import connector.endpoint.EndPoint;

import java.nio.channels.SocketChannel;

public interface Wrapper {
    SocketChannel getClient();

    Poller getPoller();

    EndPoint getServer();
}
