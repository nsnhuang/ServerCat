import lombok.extern.slf4j.Slf4j;
import server.Server;

import java.util.Scanner;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-22 3:59 PM
 */
@Slf4j
public class Bootstrap implements Runnable {

    private Server server;

    @Override
    public void run() {
        server = new Server();
        server.init();
        server.start();

        Scanner scanner = new Scanner(System.in);
        String order;
        while (scanner.hasNext()) {
            order = scanner.next();
            if ("exit".equalsIgnoreCase(order)) {
                server.stop();
                System.exit(0);
            }
        }
    }
}