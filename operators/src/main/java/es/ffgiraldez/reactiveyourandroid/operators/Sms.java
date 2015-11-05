package es.ffgiraldez.reactiveyourandroid.operators;

import java.util.Date;

/**
 * @author Fernando Franco Giráldez
 */
public class Sms extends TelephonyEvent {

    private final String text;

    public Sms(Date timestamp, String partner, String text, boolean incoming) {
        super(partner, timestamp, incoming);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
