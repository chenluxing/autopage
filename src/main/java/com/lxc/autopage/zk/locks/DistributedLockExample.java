package com.lxc.autopage.zk.locks;

import com.google.common.collect.Lists;
import com.lxc.autopage.zk.CuratorFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.utils.CloseableUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁代码示例
 * Created by chenlx
 * on 2016/10/31.
 */
public class DistributedLockExample {
    private static CuratorFramework client = CuratorFactory.getClient();
    private static final String PATH = "/locks";
    // 进程内部（可重入）读写锁
    private static final InterProcessReadWriteLock lock;
    // 读锁
    private static final InterProcessLock readLock;
    // 写锁
    private static final InterProcessLock writeLock;

    static {
        client.start();
        lock = new InterProcessReadWriteLock(client, PATH);
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    public static void main(String[] args) {
        try {
            List<Thread> jobs = Lists.newArrayList();
            for (int i = 0; i < 10; i++) {
                Thread t = new Thread(new ParallelJob("Parallel 任务" + i, readLock));
                jobs.add(t);
            }
            for (int i = 0; i < 10; i++) {
                Thread t = new Thread(new MutexJob("Mutex 任务" + i, writeLock));
                jobs.add(t);
            }

            for (Thread t : jobs) {
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }


    /**
     * 并行任务
     */
    static class ParallelJob implements Runnable {

        private final String name;
        private final InterProcessLock lock;
        private final int wait_time = 5;

        ParallelJob(String name, InterProcessLock lock) {
            this.name = name;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                doWork();
            } catch (Exception e) {

            }
        }

        public void doWork() throws Exception {
            try {
                if (!lock.acquire(wait_time, TimeUnit.SECONDS)) {
                    System.err.println(name + "等待" + wait_time + "秒，仍未能获取到lock，准备放弃。");
                }
                int exeTime = new Random().nextInt(4000);
                System.out.println(name + "开始执行时间=" + exeTime + "毫秒");
                Thread.sleep(exeTime);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.release();
            }
        }
    }

    /**
     * 互斥任务
     */
    static class MutexJob implements Runnable {

        private final String name;
        private final InterProcessLock lock;
        private final int wait_time = 10;

        public MutexJob(String name, InterProcessLock lock) {
            this.name = name;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                doWork();
            } catch (Exception e) {

            }
        }

        public void doWork() throws Exception {
            try {
                if (lock.acquire(wait_time, TimeUnit.SECONDS)) {
                    System.err.println(name + "等待" + wait_time + "秒，仍未能获取到lock，准备放弃。");
                }

                int exeTime = new Random().nextInt(2000);
                System.out.println(name + "开始执行时间=" + exeTime + "毫秒");
                Thread.sleep(exeTime);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.release();
            }
        }
    }


}
