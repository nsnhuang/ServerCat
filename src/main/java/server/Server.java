package server;

import server.lifecycle.AbstractLifecycle;
import server.service.Service;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-30 2:30 PM
 */
public class Server extends AbstractLifecycle {

    private Service service;

    @Override
    protected void initInternal() {
        service = new Service();
        service.init();
    }

    @Override
    protected void startInternal() {
        service.start();
    }

    @Override
    protected void stopInternal() {
        service.stop();
    }

}