package connector.processor;

import connector.adapter.DefaultAdapter;
import entiry.Request;
import entiry.Response;
import entiry.http.Http11Processor;
import connector.endpoint.wrapper.NioSocketWrapper;
import connector.endpoint.wrapper.Wrapper;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-24 5:48 PM
 */
public class NioSocketProcessor extends AbstractSocketProcessor {

    public NioSocketProcessor(Wrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void run() {
        NioSocketWrapper nioSocketWrapper = (NioSocketWrapper) wrapper;

        // 解析http协议
        Http11Processor httpProcessor = new Http11Processor();
        Request request = httpProcessor.process(nioSocketWrapper);
        Response response = new Response();

        // 用适配器进行转化成Servlet对象
//        new DefaultAdapter(wrapper).service(request, response);




    }
}