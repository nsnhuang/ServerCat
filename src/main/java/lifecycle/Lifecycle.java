package lifecycle;

import exception.LifecycleException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author huang
 */
public interface Lifecycle {

    /**
     * The LifecycleEvent type for the "component before init" event.
     */
    public static final String BEFORE_INIT_EVENT = "before_init";


    /**
     * The LifecycleEvent type for the "component after init" event.
     */
    public static final String AFTER_INIT_EVENT = "after_init";


    /**
     * The LifecycleEvent type for the "component start" event.
     */
    public static final String START_EVENT = "start";


    /**
     * The LifecycleEvent type for the "component before start" event.
     */
    public static final String BEFORE_START_EVENT = "before_start";


    /**
     * The LifecycleEvent type for the "component after start" event.
     */
    public static final String AFTER_START_EVENT = "after_start";


    /**
     * The LifecycleEvent type for the "component stop" event.
     */
    public static final String STOP_EVENT = "stop";


    /**
     * The LifecycleEvent type for the "component before stop" event.
     */
    public static final String BEFORE_STOP_EVENT = "before_stop";


    /**
     * The LifecycleEvent type for the "component after stop" event.
     */
    public static final String AFTER_STOP_EVENT = "after_stop";


    /**
     * The LifecycleEvent type for the "component after destroy" event.
     */
    public static final String AFTER_DESTROY_EVENT = "after_destroy";


    /**
     * The LifecycleEvent type for the "component before destroy" event.
     */
    public static final String BEFORE_DESTROY_EVENT = "before_destroy";



    /**
     * 组件的初始化方法
     */
    void init() throws LifecycleException;

    /**
     * 组件的启动方法
     */
    void start();

    /**
     * 组件的停止放啊放
     */
    void stop();

    /**
     * 组件的销毁方法
     */
    void destroy();

    /**
     * 添加监听器
     * @param listener 监听器
     */
    void addLifecycleListener(LifecycleListener listener);

    /**
     * 移除监听器
     * @param listener 监听器
     */
    void removeLifecycleListener(LifecycleListener listener);

}
