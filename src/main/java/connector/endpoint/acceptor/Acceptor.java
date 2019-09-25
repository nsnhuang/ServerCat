package connector.endpoint.acceptor;

import connector.endpoint.EndPoint;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.SocketChannel;

@Slf4j
public class Acceptor implements Runnable {

    private volatile boolean paused = false;
    private volatile boolean running = false;
    private volatile AcceptorState state = AcceptorState.NEW;
    private String name;
    private EndPoint server;

    public Acceptor(EndPoint endPoint, String name) {
        this.server = endPoint;
        this.name = name;
    }

    @Override
    public void run() {
        log.info(Thread.currentThread().getName() + "[Acceptor:" + name +"]开始监听, 端口" + server.getPort());
        while (server.isRunning()) {
            SocketChannel client;
            try {
                client = server.accept();
                if (client == null) {
                    continue;
                }
                log.info("Acceptor[" + name +"]接到请求...[" + client + "]");
                server.registerToPoller(client);
            } catch (IOException e) {
                log.error("Acceptor[" + name + "] IOException");
            }
        }
    }


    public enum AcceptorState {
        NEW, RUNNING, PAUSED, ENDED
    }
}