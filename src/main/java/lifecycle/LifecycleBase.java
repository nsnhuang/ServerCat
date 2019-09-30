package lifecycle;

import exception.LifecycleException;
import org.omg.CORBA.INITIALIZE;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-25 10:17 PM
 */
public abstract class LifecycleBase implements Lifecycle {

    private final List<LifecycleListener> lifecycleListeners = new CopyOnWriteArrayList<>();
    private volatile LifecycleState state = LifecycleState.NEW;

    @Override
    public final synchronized void init() throws LifecycleException {
        if (!state.equals(LifecycleState.NEW)) {
            invalidTransition(Lifecycle.BEFORE_INIT_EVENT);
        }

        try {
            setStateInternal(LifecycleState.INITIALIZING, null);
            initInternal();
            setStateInternal(LifecycleState.INITIALIZED, null);
        } catch (Throwable t) {
            throw new LifecycleException("lifecycleBase.initFail" + t);
        }
    }

    /**
     * 模版方法，提供给具体对象的初始化方法
     * @throws LifecycleException
     */
    protected abstract void initInternal() throws LifecycleException;

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

    private void invalidTransition(String type) throws LifecycleException {
        throw new LifecycleException("invalidTransition LifecycleBase type: " + type +"state: " + state);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

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