package connector.endpoint.dispatcher;

import connector.processor.NioSocketProcessor;
import connector.endpoint.wrapper.Wrapper;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-23 10:16 PM
 */
public class NioDispatcher extends AbstractDispatcher {

    @Override
    public void doDispatcher(Wrapper wrapper) {
        pool.execute(new NioSocketProcessor(wrapper));
    }
}