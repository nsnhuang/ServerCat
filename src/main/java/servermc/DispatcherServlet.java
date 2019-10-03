package servermc;

import com.alibaba.fastjson.JSON;
import server.entiry.Request;
import server.entiry.Response;
import servermc.entity.Data;
import servermc.entity.Handler;
import servermc.entity.Param;
import servermc.helper.BeanHelper;
import servermc.helper.ControllerHelper;
import servermc.helper.RequestHelper;
import servermc.util.ReflectionUtil;

import javax.servlet.annotation.WebServlet;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-10-03 10:49 AM
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet implements MyServlet {

    @Override
    public void init() {
        HelpLoader.init();
    }

    @Override
    public void service(Request request, Response response) {
        String method = request.getMethod().name().toUpperCase();
        String path = request.getPath();

        Handler handler = ControllerHelper.getHandler(method, path);
        if (handler != null) {

            // 获取对象实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            Method actionMethod = handler.getControllerMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);

            handleDataResult(new Data(result), response);
        }
    }

    /**
     * 返回json数据
     * @param data 数据
     * @param response response
     */
    @Override
    public void handleDataResult(Data data, Response response) {
        Object model = data.getModel();
        if (model != null) {
            response.setContentType("application/json");
            String json = JSON.toJSON(model).toString();
            response.setBody(json.getBytes(StandardCharsets.UTF_8));
        }
    }
}