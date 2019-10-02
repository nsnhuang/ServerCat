package server.connector.protocolhandler.endpoint.poller.wrapper;

import server.connector.protocolhandler.endpoint.poller.Poller;
import server.connector.protocolhandler.endpoint.EndPoint;

import java.nio.channels.SocketChannel;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-23 11:24 AM
 */

public class NioSocketWrapper extends AbstractWrapper{

    public NioSocketWrapper(EndPoint server, SocketChannel client, Poller poller) {
        this.server = server;
        this.client = client;
        this.poller = poller;
    }
}