package container.pipeline;

import container.value.Value;

import java.util.List;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-25 5:49 PM
 */
public abstract class AbstractPipeline implements Pipeline {

    protected List<Value> values;
}