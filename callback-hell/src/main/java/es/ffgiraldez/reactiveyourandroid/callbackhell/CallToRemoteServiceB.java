package es.ffgiraldez.reactiveyourandroid.callbackhell;

/**
 * @author Fernando Franco Giráldez
 */
final class CallToRemoteServiceB implements Runnable {

    private final Callback<Integer> callback;

    CallToRemoteServiceB(Callback<Integer> callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        // simulate fetching data from remote service
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.call(100);
    }
}
