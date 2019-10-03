package servermc;

import server.entiry.Request;
import server.entiry.Response;
import servermc.entity.Data;

/**
 * @author huang
 */
public interface MyServlet {

    /**
     * 初始化方法
     */
    void init();

    /**
     * service 方法
     */
    void service(Request request, Response response);

    /**
     * 处理返回数据的方法
     * @param data 数据
     * @param response response
     */
    void handleDataResult(Data data, Response response);
}
