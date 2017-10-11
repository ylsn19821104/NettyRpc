package com.nettyrpc.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by hongxp on 2017/10/11.
 */
public class ClhLock implements Lock {
    private final ThreadLocal<Node> prev;
    private final ThreadLocal<Node> node;
    private final AtomicReference<Node> tail = new AtomicReference<>(new Node());

    public ClhLock() {
        this.node = ThreadLocal.withInitial(() -> new Node());
        this.prev = ThreadLocal.withInitial(() -> null);
    }

    /**
     * 1.初始状态 tail指向一个node(head)节点
     * +------+
     * | head | <---- tail
     * +------+
     * <p>
     * 2.lock-thread加入等待队列: tail指向新的Node，同时Prev指向tail之前指向的节点
     * +----------+
     * | Thread-A |
     * | := Node  | <---- tail
     * | := Prev  | -----> +------+
     * +----------+        | head |
     * +------+
     * <p>
     * +----------+            +----------+
     * | Thread-B |            | Thread-A |
     * tail ---->  | := Node  |     -->    | := Node  |
     * | := Prev  | ----|      | := Prev  | ----->  +------+
     * +----------+            +----------+         | head |
     * +------+
     * 3.寻找当前node的prev-node然后开始自旋
     */
    @Override
    public void lock() {
        final Node node = this.node.get();
        node.locked = true;
        Node pred = this.tail.getAndSet(node);
        this.prev.set(pred);
        while (pred.locked) ;
    }

    @Override
    public void unlock() {
        final Node node = this.node.get();
        node.locked = false;
        this.node.set(this.prev.get());
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }

    private static class Node {
        private volatile boolean locked;
    }
}
