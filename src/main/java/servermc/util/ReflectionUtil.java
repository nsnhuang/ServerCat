package servermc.util;

import java.lang.reflect.Method;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-10-03 3:48 PM
 */
public class ReflectionUtil {


    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static Object newInstance(Class<?> cls) {
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return instance;
    }
}