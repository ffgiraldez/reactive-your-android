package es.ffgiraldez.reactiveyourandroid.callbackhell;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Code extracted from https://gist.github.com/benjchristensen/4677544
 */
public class CallbackHell {

    /**
     * Demonstration of nested callbacks which then need to composes their responses together.
     * <p>
     * Various different approaches for composition can be done but eventually they end up relying upon
     * synchronization techniques such as the CountDownLatch used here or converge on callback design
     * changes similar to <a href="https://github.com/Netflix/RxJava">Rx</a>.
     */
    public static void run() throws Exception {
        final ExecutorService executor = new ThreadPoolExecutor(4, 4, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        /* the following are used to synchronize and compose the asynchronous callbacks */
        final CountDownLatch latch = new CountDownLatch(3);
        final AtomicReference<String> f3Value = new AtomicReference<>();
        final AtomicReference<Integer> f4Value = new AtomicReference<>();
        final AtomicReference<Integer> f5Value = new AtomicReference<>();

        try {
            // get f3 with dependent result from f1
            executor.execute(new CallToRemoteServiceA(f1 ->
                    executor.execute(new CallToRemoteServiceC(f3 -> {
                        // we have f1 and f3 now need to compose with others
                        System.out.println("intermediate callback: " + f3 + " => " + ("f4 * f5"));
                        // set to thread-safe variable accessible by external scope
                        f3Value.set(f3);
                        latch.countDown();
                    }, f1))
            ));

            // get f4/f5 after dependency f2 completes 
            executor.execute(new CallToRemoteServiceB(f2 -> {
                executor.execute(new CallToRemoteServiceD(f4 -> {
                    // we have f2 and f4 now need to compose with others
                    System.out.println("intermediate callback: f3" + " => " + (f4 + " * f5"));
                    // set to thread-safe variable accessible by external scope
                    f4Value.set(f4);
                    latch.countDown();
                }, f2));
                executor.execute(new CallToRemoteServiceE(f5 -> {
                    // we have f2 and f5 now need to compose with others
                    System.out.println("intermediate callback: f3" + " => " + ("f4 * " + f5));
                    // set to thread-safe variable accessible by external scope
                    f5Value.set(f5);
                    latch.countDown();
                }, f2));
            }));

            /* we must wait for all callbacks to complete */
            latch.await();
            System.out.println(f3Value.get() + " => " + (f4Value.get() * f5Value.get()));
        } finally {
            executor.shutdownNow();
        }
    }

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}