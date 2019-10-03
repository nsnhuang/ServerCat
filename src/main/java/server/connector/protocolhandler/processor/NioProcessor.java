package server.connector.protocolhandler.processor;

import server.connector.adapter.Adapter;
import server.connector.protocolhandler.endpoint.poller.wrapper.NioSocketWrapper;
import server.connector.protocolhandler.endpoint.poller.wrapper.Wrapper;
import server.entiry.Request;
import server.entiry.Response;
import server.entiry.http.Http11Processor;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-24 5:48 PM
 */
public class NioProcessor extends AbstractProcessor {

    public NioProcessor(Wrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void run() {
        NioSocketWrapper nioSocketWrapper = (NioSocketWrapper) wrapper;

        // 解析http协议
        Http11Processor httpProcessor = new Http11Processor();
        Request request = httpProcessor.process(nioSocketWrapper);
        Response response = new Response();

        new Adapter(nioSocketWrapper).service(request, response);

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