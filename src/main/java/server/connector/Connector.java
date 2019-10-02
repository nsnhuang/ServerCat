package server.connector;

import server.connector.adapter.Adapter;
import server.connector.protocolhandler.ProtocolHandler;
import server.lifecycle.AbstractLifecycle;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-30 1:53 PM
 */
public class Connector extends AbstractLifecycle {

    private ProtocolHandler protocolHandler;
    private Adapter adapter;

    @Override
    protected void initInternal() {
        this.protocolHandler = new ProtocolHandler();
        this.adapter = new Adapter();
        protocolHandler.init();
        adapter.init();
    }

    @Override
    protected void startInternal() {
        protocolHandler.start();
        adapter.start();
    }

    @Override
    protected void stopInternal() {
        protocolHandler.stop();
        adapter.stop();
    }
}