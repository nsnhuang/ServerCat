package server.container.value;

import server.entiry.ServletRequest;
import server.entiry.ServletResponse;

public interface Value {
  public Value getNext();
  public void setNext(Value value);
  public void invoke(ServletRequest request, ServletResponse response);
}
