package server.connector.adapter;

import com.sun.deploy.net.HttpRequest;
import server.connector.protocolhandler.endpoint.poller.wrapper.NioSocketWrapper;
import server.connector.protocolhandler.endpoint.poller.wrapper.Wrapper;
import server.entiry.Request;
import server.entiry.Response;
import server.lifecycle.AbstractLifecycle;
import servermc.DispatcherServlet;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-25 4:33 PM
 */
public class Adapter extends AbstractLifecycle {

    private Wrapper wrapper;

    public Adapter(NioSocketWrapper nioSocketWrapper) {
        wrapper = nioSocketWrapper;
    }

    public void service(Request request, Response response) {
        // 调用容器
        new DispatcherServlet().service(request, response);

        try {
            ByteBuffer[] buffer = response.getResponseByteBuffer();
            wrapper.getClient().write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initInternal() {

    }

    @Override
    protected void startInternal() {

    }

    @Override
    protected void stopInternal() {

    }
}