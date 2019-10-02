package server.lifecycle;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-25 10:17 PM
 */
public abstract class AbstractLifecycle implements Lifecycle {

    private final List<LifecycleListener> lifecycleListeners = new CopyOnWriteArrayList<>();
    private volatile LifecycleState state = LifecycleState.NEW;

    @Override
    public final synchronized void init() {
        setStateInternal(LifecycleState.INITIALIZING, null);
        initInternal();
        setStateInternal(LifecycleState.INITIALIZED, null);
    }

    /**
     * 模版方法，提供给具体对象的初始化方法
     */
    protected abstract void initInternal();

    private void setStateInternal(LifecycleState state, Object data) {
        this.state = state;
        String lifecycleEvent = state.getLifecycleEvent();
        if (lifecycleEvent != null) {
            fireLifecycleEvent(lifecycleEvent, data);
        }
    }

    private void fireLifecycleEvent(String lifecycleEvent, Object data) {
        LifecycleEvent event = new LifecycleEvent(this, lifecycleEvent, data);
        for (LifecycleListener listener : lifecycleListeners) {
                listener.lifecycleEvent(event);
        }
    }

    @Override
    public void start() {
        setStateInternal(LifecycleState.STARTING_PREP, null);
        startInternal();
        setStateInternal(LifecycleState.STARTED, null);
    }

    /**
     * 模版方法，提供具体的对象start方法
     */
    protected abstract void startInternal();


    @Override
    public void stop() {
        setStateInternal(LifecycleState.STOPPING_PREP, null);
        stopInternal();
        setStateInternal(LifecycleState.STOPPED, null);
    }

    /**
     * 模版方法，提供具体的对象stop方法
     */
    protected abstract void stopInternal();


    @Override
    public server.lifecycle.LifecycleState getState() {
        return state;
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycleListeners.add(listener);
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycleListeners.remove(listener);
    }
}