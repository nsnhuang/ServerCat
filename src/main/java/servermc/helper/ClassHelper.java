package servermc.helper;

import servermc.annotation.Controller;
import servermc.annotation.Service;
import servermc.util.ClassUtil;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-10-03 12:57 PM
 */
public class ClassHelper {

    private static final Set<Class<?>> CLASS_SET ;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }

    private static Collection<? extends Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Service.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls :CLASS_SET) {
            if (cls.isAnnotationPresent(Controller.class)) {
                classSet.add(cls);
            }

        }
        return classSet;
    }
}