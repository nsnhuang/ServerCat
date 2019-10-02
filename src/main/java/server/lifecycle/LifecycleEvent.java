package server.lifecycle;

import java.util.EventObject;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-25 10:09 PM
 */
public class LifecycleEvent extends EventObject {

    private final Object data;
    private final String type;

    /**
     * Constructs a prototypical Event.
     */
    public LifecycleEvent(Lifecycle lifecycle, String type, Object data) {
        super(lifecycle);
        this.type = type;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public Lifecycle getLifecycle() {
        return (Lifecycle) getSource();
    }

    public String getType() {
        return type;
    }
}