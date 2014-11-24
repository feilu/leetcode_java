package concurrency;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implemented a simple blocking queue. Use lock and two conditions
 * 
 * @author flu
 * 
 */
public class BlockingQueue<T> {

    private int maxLen;
    private Queue<T> queue;
    private Lock lock;
    private Condition notEmpty;
    private Condition notFull;
    
    public BlockingQueue(int size) {
        maxLen = size;
        queue = new ArrayDeque<>();
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void offer(T o) throws InterruptedException {
        try {
            lock.lock();
            while (queue.size() == maxLen) {
                System.out.println("Full queue, waiting...");
                notFull.await();
            }

            queue.offer(o);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T poll() throws InterruptedException {
        try {
            lock.lock();
            while (queue.size() == 0) {
                System.out.println("Empty queue, waiting...");
                notEmpty.await();
            }

            T o = queue.poll();
            notFull.signal();
            return o;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final BlockingQueue<Integer> q = new BlockingQueue<>(10);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Producer(q));
        exec.execute(new Producer(q));
        exec.execute(new Producer(q));
        exec.execute(new Consumer(q));
        exec.shutdown();
    }
}

class Producer implements Runnable {
    private static final Random random = new Random();

    private BlockingQueue<Integer> queue;
    private int n = 0;

    public Producer(BlockingQueue<Integer> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        while (n++ < 5) {
            int i = random.nextInt(100);
            System.out.println(Thread.currentThread() + " putting " + i);
            try {
                queue.offer(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;
    private int n = 0;

    public Consumer(BlockingQueue<Integer> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        while (n++ < 10) {
            try {
                int i = queue.poll();
                System.out.println(Thread.currentThread() + " getting " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}