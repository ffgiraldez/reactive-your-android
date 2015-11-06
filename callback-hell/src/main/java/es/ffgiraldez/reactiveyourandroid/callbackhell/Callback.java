package es.ffgiraldez.reactiveyourandroid.callbackhell;

/**
 * @author Fernando Franco Giráldez
 */
interface Callback<T> {
    void call(T value);
}
