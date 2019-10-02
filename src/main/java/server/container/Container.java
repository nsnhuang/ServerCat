package server.container;

import server.container.pipeline.Pipeline;
import server.lifecycle.Lifecycle;

public interface Container extends Lifecycle {

    public void addChild(Container child);

    public Container findChild(String name);

    public Container getParent();

    public void setParent(Container container);

    public void removeChild(Container child);

    public void setName(String name);

    public String getName();

    public Pipeline getPipeline();
}