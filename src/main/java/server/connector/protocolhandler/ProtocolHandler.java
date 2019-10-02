package server.connector.protocolhandler;

import lombok.extern.slf4j.Slf4j;
import server.connector.protocolhandler.endpoint.AioEndPoint;
import server.connector.protocolhandler.endpoint.BioEndPoint;
import server.connector.protocolhandler.endpoint.EndPoint;
import server.connector.protocolhandler.endpoint.NioEndPoint;
import server.lifecycle.AbstractLifecycle;
import server.util.PropertiesUtil;
import server.util.StringUtil;

import java.util.concurrent.*;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-30 1:56 PM
 */
@Slf4j
public class ProtocolHandler extends AbstractLifecycle {

    private final static String AIO = "aio";
    private final static String BIO = "bio";
    private final static String NIO = "nio";

    private EndPoint endPoint;

    @Override
    protected void initInternal() {
        // 获取 port
        int port = 0;
        port = Integer.parseInt(PropertiesUtil.getProperty("server.port"));
        if (port == 0) {
            log.info("server.port 非法");
            // 停止服务器

        }

        // 获取连接方式
        String connector = null;
        String conn = null;
        connector = PropertiesUtil.getProperty("server.connector");
        conn = StringUtil.findEqual(connector, BIO, AIO, NIO);
        if (null == conn) {
            log.info("server.connect 非法");
            // 停止服务器

        }

        assert conn != null;
        switch (conn) {
            case NIO:
                endPoint = new NioEndPoint(port);
                break;
            case BIO:
                endPoint = new BioEndPoint(port);
                break;
            case AIO:
                endPoint = new AioEndPoint(port);
                break;
            default:
                endPoint = new NioEndPoint(port);
        }

        endPoint.init();
    }

    @Override
    protected void startInternal() {
        endPoint.start();
    }

    @Override
    protected void stopInternal() {

    }

}