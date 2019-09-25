package connector.endpoint;

import connector.endpoint.wrapper.Wrapper;
import container.Container;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @author huang
 */
public interface EndPoint {
    Container getContext();

    void execute(Wrapper attachment);

    void registerToPoller(SocketChannel client) throws IOException;

    /**
     * 服务器启动方法
     */
    void start();

    /**
     * 服务器关闭方法
     */
    void close();

    /**
     * 返回端口
     */
    int getPort();

    boolean isRunning();

    SocketChannel accept() throws IOException;
}
