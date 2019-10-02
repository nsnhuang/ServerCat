package server.connector.protocolhandler.processor;


import server.connector.protocolhandler.endpoint.poller.wrapper.Wrapper;
import server.lifecycle.AbstractLifecycle;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-24 5:48 PM
 */
public abstract class AbstractProcessor extends AbstractLifecycle implements Runnable {
    protected Wrapper wrapper;

    public AbstractProcessor(Wrapper wrapper) {
        this.wrapper = wrapper;
    }
}