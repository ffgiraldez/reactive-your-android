package es.ffgiraldez.reactiveyourandroid.callbackhell;

/**
 * @author Fernando Franco Giráldez
 */
final class CallToRemoteServiceA implements Runnable {

    private final Callback<String> callback;

    CallToRemoteServiceA(Callback<String> callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        // simulate fetching data from remote service
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.call("responseA");
    }
}
