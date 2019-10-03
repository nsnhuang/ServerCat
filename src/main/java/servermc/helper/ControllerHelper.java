package servermc.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import servermc.annotation.RequestMapping;
import servermc.entity.Handler;
import servermc.entity.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 描述:
 * 用于加载RequestMapping注解的方法、类
 * 获取request对应的handler
 * @author huang
 * @create 2019-10-03 12:58 PM
 */
public class ControllerHelper {

    private static final Map<Request, Handler> REQUEST_MAP = new HashMap<>();

    static {
        // 获取 controller class set
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            for (Class<?> controllerClass : controllerClassSet) {

                // 遍历每一个controller class
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {

                            // 获取controller class 的每一个 有RequestMapping注解的 方法
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String requestPath = requestMapping.value();
                            String requestMethod = requestMapping.method().name();

                            // 封装请求路径path、请求方法method 保存到 REQUEST_MAP 中
                            Request request = new Request(requestMethod, requestPath);
                            Handler handler = new Handler(controllerClass, method);
                            REQUEST_MAP.put(request, handler);
                        }

                    }
                }

            }
        }
    }

    public static Handler getHandler(String method, String path) {

        Request request = new Request(method, path);
        return REQUEST_MAP.get(request);
    }
}