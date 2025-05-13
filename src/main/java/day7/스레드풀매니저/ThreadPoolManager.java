package day7.스레드풀매니저;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// Bill Pugh
public class ThreadPoolManager {

    private static class DaemonThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DaemonThreadFactory(String poolName) {
            this.group = Thread.currentThread().getThreadGroup();
            this.namePrefix = "pool-" + poolName + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);

            // 데몬 스레드로 설정
            if (!t.isDaemon()) {
                t.setDaemon(true);
            }

            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }

            return t;
        }
    }

    // 고정 크기 스레드 풀 (CPU 집약적 작업용)
    private final ExecutorService cpuBoundExecutor;

    // 가변 크기 스레드 풀 (I/O 집약적 작업용)
    private final ExecutorService ioBoundExecutor;

    // 주기적 작업용 스케쥴 스레드 풀
    private final ScheduledExecutorService scheduledExecutorService;

    private static class InstanceHolder {
        private static final ThreadPoolManager INSTANCE = new ThreadPoolManager();
    }

    private ThreadPoolManager() {
        // CPU 코어수 기반으로 고정크기 스레드풀 생성
        int cpuCores = Runtime.getRuntime().availableProcessors();

        this.cpuBoundExecutor = Executors.newFixedThreadPool(
                cpuCores,
                new DaemonThreadFactory("cpu")
        );

        // I/O
        this.ioBoundExecutor = Executors.newFixedThreadPool(
                cpuCores * 2,
                new DaemonThreadFactory("io")
        );

        this.scheduledExecutorService = Executors.newScheduledThreadPool(
                1,
                new DaemonThreadFactory("scheduled")
        );
    }

    public static ThreadPoolManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void execute(Runnable task, boolean isCpuBound) {
        if (isCpuBound) {
            cpuBoundExecutor.execute(task);
        } else {
            ioBoundExecutor.execute(task);
        }
    }

    public void scheduleAtFixedRate(Runnable task, long initialDelay, long period) {
        scheduledExecutorService.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        cpuBoundExecutor.shutdown();
        ioBoundExecutor.shutdown();
        scheduledExecutorService.shutdown();
    }
}
