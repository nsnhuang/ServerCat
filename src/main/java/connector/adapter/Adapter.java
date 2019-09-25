package connector.adapter;

import entiry.Request;
import entiry.Response;

/**
 * @author huang
 */
public interface Adapter {

    /**
     * 适配器的适配方法
     * @param request ServerCat request对象
     * @param response ServerCat response对象
     */
    void service(Request request, Response response);
}
