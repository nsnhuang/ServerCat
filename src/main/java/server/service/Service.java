package server.service;

import server.connector.Connector;
import server.container.Handler;
import server.lifecycle.AbstractLifecycle;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-30 1:46 PM
 */
public class Service extends AbstractLifecycle {

    private Connector connector;
    private Handler handler;

    @Override
    protected void initInternal() {
        this.connector = new Connector();
        this.handler = new Handler();
        connector.init();
        handler.init();
    }

    @Override
    protected void startInternal() {
        connector.start();
        handler.start();
    }

    @Override
    protected void stopInternal() {
        connector.stop();
        handler.stop();
    }

}