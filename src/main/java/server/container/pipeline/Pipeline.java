package server.container.pipeline;

import server.container.value.Value;

public interface Pipeline {
  public void addValve(Value value);
  public Value getBasic();
  public void setBasic(Value value);
  public Value getFirst();
}
