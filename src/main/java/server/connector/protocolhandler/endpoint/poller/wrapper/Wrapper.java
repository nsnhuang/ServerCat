package server.connector.protocolhandler.endpoint.poller.wrapper;

import server.connector.protocolhandler.endpoint.poller.Poller;
import server.connector.protocolhandler.endpoint.EndPoint;

import java.nio.channels.SocketChannel;

public interface Wrapper {
    SocketChannel getClient();

    Poller getPoller();

    EndPoint getServer();
}
