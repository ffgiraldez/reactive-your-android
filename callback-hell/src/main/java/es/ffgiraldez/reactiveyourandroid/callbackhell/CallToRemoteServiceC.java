package es.ffgiraldez.reactiveyourandroid.callbackhell;

/**
 * @author Fernando Franco Giráldez
 */
final class CallToRemoteServiceC implements Runnable {

    private final Callback<String> callback;
    private final String dependencyFromA;

    CallToRemoteServiceC(Callback<String> callback, String dependencyFromA) {
        this.callback = callback;
        this.dependencyFromA = dependencyFromA;
    }

    @Override
    public void run() {
        // simulate fetching data from remote service
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.call("responseB_" + dependencyFromA);
    }
}
