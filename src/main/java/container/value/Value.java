package container.value;

import entiry.ServletRequest;
import entiry.ServletResponse;

public interface Value {
  public Value getNext();
  public void setNext(Value value);
  public void invoke(ServletRequest request, ServletResponse response);
}
