package container;

import container.pipeline.Pipeline;
import lifecycle.LifecycleBase;

import java.util.HashMap;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-25 5:15 PM
 */
public abstract class AbstractContainer extends LifecycleBase implements Container {

    protected HashMap<String, Container> children = new HashMap<>();
    protected Container parent;
    protected String name;
    protected Pipeline pipeline;
}