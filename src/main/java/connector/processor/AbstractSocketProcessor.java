package connector.processor;


import connector.endpoint.wrapper.Wrapper;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-24 5:48 PM
 */
public abstract class AbstractSocketProcessor implements SocketProcessor {
    protected Wrapper wrapper;

    public AbstractSocketProcessor(Wrapper wrapper) {
        this.wrapper = wrapper;
    }
}