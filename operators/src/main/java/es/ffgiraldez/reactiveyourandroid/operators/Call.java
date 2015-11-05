package es.ffgiraldez.reactiveyourandroid.operators;

import java.util.Date;

/**
 * @author Fernando Franco Giráldez
 */
public class Call extends TelephonyEvent {

    private final long duration;

    public Call(Date timestamp, String partner, long duration, boolean incoming) {
        super(partner, timestamp, incoming);
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }
}
