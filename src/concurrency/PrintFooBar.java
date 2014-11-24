package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * print 'foo' and 'bar' one after another by two threads, one only prints 'foo', the other only prints 'bar'.
 * 
 * @author flu
 * 
 */
public class PrintFooBar {

    public void print() {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0); // make sure foo is printed first.

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Printer("foo", s1, s2));
        exec.execute(new Printer("bar", s2, s1));
        exec.shutdown();
    }

    public static void main(String[] args) {
        new PrintFooBar().print();
    }
}

class Printer implements Runnable {

    private Semaphore s1;
    private Semaphore s2;
    private String str;
    private int n = 5;

    public Printer(String prtStr, Semaphore s1, Semaphore s2) {
        this.str = prtStr;
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i++ < n) {
                s1.acquire();
                System.out.println(str);
                s2.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
