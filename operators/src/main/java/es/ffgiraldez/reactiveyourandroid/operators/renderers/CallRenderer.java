package es.ffgiraldez.reactiveyourandroid.operators.renderers;

import es.ffgiraldez.reactiveyourandroid.operators.Call;

/**
 * @author Fernando Franco Giráldez
 */
public abstract class CallRenderer extends TelephonyEventRenderer {

    @Override
    public void render() {
        Call call = (Call) getContent();
        text.setText(
                String.format(
                        "%s call from %s with duration %d \nat %s %s",
                        call.isIncoming() ? "Received" : "Sent",
                        call.getPartner(),
                        call.getDuration(),
                        dateFormat.format(call.getTimestamp()),
                        timeFormat.format(call.getTimestamp())
                )
        );
    }
}
