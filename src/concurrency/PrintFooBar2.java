package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * see {@Link PrintFooBar}. Here uses an AtomicInteger to do the switch.
 * 
 * @author flu
 * 
 */
public class PrintFooBar2 {

    public void print(int n) {
        AtomicBoolean flag = new AtomicBoolean(true);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Worker("foo", flag, n / 2, true));
        exec.execute(new Worker("bar", flag, n / 2, false));
        exec.shutdown();
    }

    public static void main(String[] args) {
        new PrintFooBar2().print(8);
    }
}

class Worker implements Runnable {

    private AtomicBoolean flag;
    private boolean signal;
    private String str;
    private int max;

    public Worker(String prtStr, AtomicBoolean flag, int max, boolean signal) {
        this.str = prtStr;
        this.flag = flag;
        this.max = max;
        this.signal = signal;
    }

    @Override
    public void run() {
        int i = 0;
        while (i++ < max) {
            synchronized (flag) {
                // wait for correct signal
                while (flag.get() != signal) {
                    try {
                        flag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(str);
                flag.set(!signal);
                flag.notify();
            }
        }
    }
}