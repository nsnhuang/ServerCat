package server.connector.protocolhandler.endpoint;

import lombok.extern.slf4j.Slf4j;
import server.lifecycle.AbstractLifecycle;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-22 2:14 PM
 */
@Slf4j
public abstract class AbstractEndPoint extends AbstractLifecycle implements EndPoint {

    protected int port;


    @Override
    public int getPort() {
        return port;
    }



}