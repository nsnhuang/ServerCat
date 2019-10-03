package servermc;

import servermc.helper.*;
import servermc.util.ClassUtil;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-10-03 12:54 PM
 */
public class HelpLoader {

    public static void init() {
        Class<?> [] classes = {ClassHelper.class, BeanHelper.class, AopHelper.class, IocHelper.class, ControllerHelper.class};
        for (Class<?> cls: classes) {
            ClassUtil.loadClass(cls.getName());
        }
    }

}