package servermc.entity;

import java.lang.reflect.Method;

/**
 * 描述:
 * 封装controller的class对象和method方法
 * @author huang
 * @create 2019-10-03 1:23 PM
 */
public class Handler {

    private Class<?> controllerClass;

    private Method controllerMethod;

    public Handler(Class<?> controllerClass, Method controllerMethod) {
        this.controllerClass = controllerClass;
        this.controllerMethod = controllerMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getControllerMethod() {
        return controllerMethod;
    }
}