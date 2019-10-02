package server.lifecycle;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-25 10:09 PM
 */
public interface LifecycleListener {

    /**
     * 提供给用户的接口
     * @param event
     */
    void lifecycleEvent(LifecycleEvent event);

}