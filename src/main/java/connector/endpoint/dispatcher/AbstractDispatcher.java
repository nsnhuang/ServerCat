package connector.endpoint.dispatcher;

import connector.endpoint.wrapper.Wrapper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author huang
 * @create 2019-09-23 10:04 PM
 */
public abstract class AbstractDispatcher implements Dispatcher {
    protected ThreadPoolExecutor pool;

    public AbstractDispatcher() {
        pool = new ThreadPoolExecutor(
                100,
                120,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),
                new ThreadFactory() {
                    private int count;
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "workerThread-" + count ++);
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy());
    }


    @Override
    public abstract void doDispatcher(Wrapper wrapper);

}