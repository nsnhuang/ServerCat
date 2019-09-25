package container;

import container.pipeline.Pipeline;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-25 5:15 PM
 */
public class DefaultContainer extends AbstractContainer {

    @Override
    public void addChild(Container child) {
        children.put(child.getName(),child);
    }

    @Override
    public Container findChild(String name) {
        return children.get(name);
    }

    @Override
    public Container getParent() {
        return parent;
    }

    @Override
    public void setParent(Container container) {
        this.parent = container;
    }

    @Override
    public void removeChild(Container child) {
        this.children.remove(child.getName());
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Pipeline getPipeline() {
        return pipeline;
    }
}