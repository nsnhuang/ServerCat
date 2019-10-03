package server.service;

import server.connector.Connector;
import server.lifecycle.AbstractLifecycle;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-30 1:46 PM
 */
public class Service extends AbstractLifecycle {

    private Connector connector;

    @Override
    protected void initInternal() {
        this.connector = new Connector();
        connector.init();
    }

    @Override
    protected void startInternal() {
        connector.start();
    }

    @Override
    protected void stopInternal() {
        connector.stop();
    }

}