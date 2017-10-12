package com.nettyrpc.util;

import java.io.Serializable;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by hongxp on 2017/10/11.
 * CLH lock queue其实就是一个FIFO的队列，队列中的每个结点（线程）只要等待其前继释放锁就可以了
 */

public class SimpleLock extends AbstractQueuedSynchronizer implements Serializable {

    private static final long serialVersionUID = -8778318594136137991L;

    public static void main(String[] args) throws InterruptedException {
        final SimpleLock lock = new SimpleLock();
        lock.lock();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    System.err.println(Thread.currentThread().getId() + " acquired the lock!");
                } finally {
                    lock.unLock();
                }
            }).start();
            Thread.sleep(100);
        }

        System.err.println("main thread unlock!");
        lock.unLock();
    }


    @Override
    protected boolean tryAcquire(int unused) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int unused) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock() {
        acquire(1);
    }

    public boolean tryLock() {
        return tryAcquire(1);
    }

    public void unLock() {
        release(1);
    }

    public boolean isLocked() {
        return getState() != 0;
    }
}
