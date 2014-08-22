package com.leetcode.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Unbounded nonblocking queue. http://www.cs.rochester.edu/research/synchronization/pseudocode/queues.html
 * 
 * @author flu
 * 
 */
public class ConcurrentNonBlockingQueue<T> {

    private AtomicReference<Node<T>> head;
    private AtomicReference<Node<T>> tail;

    public ConcurrentNonBlockingQueue() {
        Node<T> node = new Node<>(null);
        head = new AtomicReference<>(node);
        tail = new AtomicReference<>(node);
    }

    public void put(T val) {
        Node<T> node = new Node<>(val);

        Node<T> tl;
        while (true) {
            tl = tail.get();

            if (tl == tail.get()) {
                // tail hasn't changed
                if (tl.next == null) {

                    // atomically set tl.next to next, expecting tl.next is null, otherwise go back to loop
                    if (tl.compareAndSetNext(null, node))
                        break;
                } else {
                    // try to move tail to tl's next node, and retry
                    tail.compareAndSet(tl, tl.next);
                }
            }
        }

        // move tail forward, this could fail, but that means another thread has already updated the tail
        tail.compareAndSet(tl, node);
    }

    public T get() {
        T val;
        while (true) {
            Node<T> headNode = head.get();
            Node<T> tailNode = tail.get();

            // has head changed?
            if (headNode == head.get()) {
                // is queue empty or tail falling behind?
                if (headNode == tailNode) {
                    if (headNode.next == null) {
                        // empty queue
                        return null;
                    } else {
                        // tail falling behind, moving tail
                        tail.compareAndSet(tailNode, headNode.next);
                    }
                } else {
                    // read value
                    val = headNode.next.value;

                    // move head forward
                    if (head.compareAndSet(headNode, headNode.next)) {
                        return val;
                    }
                }
            }
        }
    }

    private static class Node<T> {
        final T value;
        
        // must be volatile
        private volatile Node<T> next;

        @SuppressWarnings("rawtypes")
        private static final AtomicReferenceFieldUpdater<Node, Node> nextUpdater = AtomicReferenceFieldUpdater
                        .newUpdater(Node.class, Node.class, "next");
        
        Node(T t) {
            this.value = t;
        }

        // atomic updater to set next node
        boolean compareAndSetNext(Node<T> expect, Node<T> next) {
            return nextUpdater.compareAndSet(this, expect, next);
        }
    }

    public static void testRun(ExecutorService exec) {
        final ConcurrentNonBlockingQueue<Integer> q = new ConcurrentNonBlockingQueue<>();
        final CountDownLatch latch = new CountDownLatch(1);

        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                    System.out.println(Thread.currentThread() + ": " + q.get());
                } catch (InterruptedException e) {
                }
            }
        });

        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                    System.out.println(Thread.currentThread() + ": " + q.get());
                } catch (InterruptedException e) {
                }
            }
        });

        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                    q.put(1);
                } catch (InterruptedException e) {
                }
            }
        });

        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                    q.put(2);
                } catch (InterruptedException e) {
                }
            }
        });


        latch.countDown(); // start!
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        int i = 1;
        while (i <= 10) {
            System.out.println("test run #" + i++);
            testRun(exec);
            TimeUnit.MILLISECONDS.sleep(20);
        }

        exec.shutdown();
        while (!exec.isTerminated()) {
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
