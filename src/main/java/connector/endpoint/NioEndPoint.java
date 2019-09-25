package connector.endpoint;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-22 1:30 PM
 */
@Slf4j
public class NioEndPoint extends AbstractEndPoint {

    public NioEndPoint() {
        this(8080);
    }

    public NioEndPoint(int port) {
        this.port = port;

        try {
            this.ssChannel = ServerSocketChannel.open();
            ssChannel.bind(new InetSocketAddress(port));
            ssChannel.configureBlocking(true);
        } catch (IOException e) {
            log.error("ssChannel IOException");
        }
    }

}