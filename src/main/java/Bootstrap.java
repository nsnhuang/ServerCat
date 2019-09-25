import connector.endpoint.EndPoint;
import connector.endpoint.NioEndPoint;
import util.PropertiesUtil;

import lombok.extern.slf4j.Slf4j;
import util.StringUtil;

import java.util.Scanner;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-22 3:59 PM
 */
@Slf4j
public class Bootstrap implements Runnable {

    private final static String BIO = "bio";
    private final static String AIO = "aio";
    private final static String NIO = "nio";

    @Override
    public void run() {
        String port = PropertiesUtil.getProperty("server.port");
        if (null == port) {
            log.info("server.port 非法");
            throw new IllegalArgumentException("server.port 非法");
        }

        String connector = PropertiesUtil.getProperty("server.connector");
        String conn = StringUtil.findEqual(connector,BIO, AIO, NIO);
        if (null == connector || null == conn) {
            log.info("server.connect 非法");
            throw new IllegalArgumentException("server.connector 非法");
        }

        int nport = Integer.valueOf(port);
        EndPoint endPoint;
        switch (conn) {
            case NIO: endPoint = new NioEndPoint(nport);break;
            default: endPoint = new NioEndPoint(nport);
        }
        endPoint.start();

        Scanner scanner = new Scanner(System.in);
        String order;
        while (scanner.hasNext()) {
            order = scanner.next();
            if ("exit".equalsIgnoreCase(order)) {
                endPoint.close();
                System.exit(0);
            }
        }
    }
}