package club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class RedisTaskService<T extends Serializable> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected Integer threadNum = 5;
    protected Integer sleepTime  = 2;
    protected String namePrefix  = "default-thread-pool-";
    protected ExecutorService pool = null;

    private RedisQueue<T> queue;

    @PreDestroy
    public void preDestroy(){
        pool.shutdownNow();
    }

    public RedisTaskService(RedisQueue<T> queue, Integer threadNum, Integer sleepTime, String namePrefix){
        this.threadNum = threadNum;
        this.sleepTime = sleepTime;
        this.namePrefix = namePrefix + "-task-pool-";
        this.queue = queue;
        this.init();
    }



    public void put(T t ){
        try {
            queue.push(t);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    protected void init() {
        pool = Executors.newFixedThreadPool(threadNum, new TrackThreadFactory());
        new Thread(() -> {
            while (!pool.isShutdown() && !pool.isTerminated()) {
                try {
                    T t = queue.poll();
                    if (t == null) {
                        TimeUnit.SECONDS.sleep(sleepTime);
                        continue;
                    }
                    pool.execute(() ->{
                        try {
                            dealWith(t);
                        }catch (Exception e){
                            logger.error("{} service task exe fail -> {}", getClass().getSimpleName(),  e);
                        }
                    });
                } catch (Exception e) {
                    logger.error("{} service exe task error", getClass().getSimpleName(), e);
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            logger.error("{} service thread stop", getClass().getSimpleName());
        }).start();
    }


     public class TrackThreadFactory implements ThreadFactory {
        private final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
//        private final String namePrefix;

        TrackThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
//            namePrefix = "track-pool-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }



    public abstract void dealWith(T t);


}
