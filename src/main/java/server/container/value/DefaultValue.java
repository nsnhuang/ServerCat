package server.container.value;

import server.entiry.ServletRequest;
import server.entiry.ServletResponse;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-25 5:57 PM
 */
public class DefaultValue extends AbstractValue {

    @Override
    public Value getNext() {
        return next;
    }

    @Override
    public void setNext(Value value) {
        next = value;
    }

    @Override
    public void invoke(ServletRequest request, ServletResponse response) {
        // 处理

        // 交给下一个value
        getNext().invoke(request, response);
    }
}