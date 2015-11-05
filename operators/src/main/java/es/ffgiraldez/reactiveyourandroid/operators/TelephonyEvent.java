package es.ffgiraldez.reactiveyourandroid.operators;

import java.util.Date;

/**
 * @author Fernando Franco Giráldez
 */
public abstract class TelephonyEvent {
    private final String partner;
    private final Date timestamp;
    private final boolean incoming;

    public TelephonyEvent(String partner, Date timestamp, boolean incoming) {
        this.partner = partner;
        this.timestamp = timestamp;
        this.incoming = incoming;
    }

    public String getPartner() {
        return partner;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public boolean isIncoming() {
        return incoming;
    }
}
