package connector.adapter;

import connector.endpoint.wrapper.Wrapper;
import entiry.Request;
import entiry.Response;
import entiry.ServletRequest;
import entiry.ServletResponse;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-25 4:33 PM
 */
public class DefaultAdapter implements Adapter {

    private Wrapper wrapper;

    @Override
    public void service(Request request, Response response) {
        // request、response对象的转换
        ServletRequest servletRequest = new ServletRequest();
        ServletResponse servletResponse = new ServletResponse();

        // 调用容器
        wrapper.getServer().getContext().getPipeline().getFirst().invoke(servletRequest, servletResponse);
    }

    public DefaultAdapter(Wrapper wrapper) {
        this.wrapper = wrapper;
    }
}