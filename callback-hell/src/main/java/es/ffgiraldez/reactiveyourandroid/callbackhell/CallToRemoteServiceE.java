package es.ffgiraldez.reactiveyourandroid.callbackhell;

/**
 * @author Fernando Franco Giráldez
 */
final class CallToRemoteServiceE implements Runnable {

    private final Callback<Integer> callback;
    private final Integer dependencyFromB;

    CallToRemoteServiceE(Callback<Integer> callback, Integer dependencyFromB) {
        this.callback = callback;
        this.dependencyFromB = dependencyFromB;
    }

    @Override
    public void run() {
        // simulate fetching data from remote service
        try {
            Thread.sleep(55);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.call(5000 + dependencyFromB);
    }
}
