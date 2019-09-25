package container.pipeline;

import container.value.Value;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-25 5:49 PM
 */
public class DefaultPipeline extends AbstractPipeline {

    @Override
    public void addValve(Value value) {
        this.values.add(value);
    }

    @Override
    public Value getBasic() {
        return this.values.get(values.size() - 1);
    }

    @Override
    public void setBasic(Value value) {
        this.values.set(values.size() - 1, value);
    }

    @Override
    public Value getFirst() {
        return this.values.get(0);
    }
}