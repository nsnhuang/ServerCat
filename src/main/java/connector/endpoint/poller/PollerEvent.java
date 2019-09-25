package connector.endpoint.poller;

import connector.endpoint.wrapper.Wrapper;

import java.io.IOException;
import java.nio.channels.SelectionKey;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class PollerEvent implements Runnable {

        private Wrapper wrapper;

        public PollerEvent(Wrapper wrapper) {
            this.wrapper = wrapper;
        }

        @Override
        public void run() {
            try {
                if (wrapper.getClient().isOpen()) {
                    wrapper.getClient().configureBlocking(false);
                    wrapper.getClient().register(wrapper.getPoller().getSelector(), SelectionKey.OP_READ, wrapper);
                } else {
                    log.info("client(SocketChannel) 已经关闭");
                }
            } catch (IOException e) {
                log.error("client register IOException");
            }
        }
    }