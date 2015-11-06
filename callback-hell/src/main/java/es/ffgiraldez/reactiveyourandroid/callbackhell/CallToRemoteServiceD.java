package es.ffgiraldez.reactiveyourandroid.callbackhell;

/**
 * @author Fernando Franco Giráldez
 */
final class CallToRemoteServiceD implements Runnable {

    private final Callback<Integer> callback;
    private final Integer dependencyFromB;

    CallToRemoteServiceD(Callback<Integer> callback, Integer dependencyFromB) {
        this.callback = callback;
        this.dependencyFromB = dependencyFromB;
    }

    @Override
    public void run() {
        // simulate fetching data from remote service
        try {
            Thread.sleep(140);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.call(40 + dependencyFromB);
    }
}
