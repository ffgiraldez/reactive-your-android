package es.ffgiraldez.reactiveyourandroid.operators.renderers;

import es.ffgiraldez.reactiveyourandroid.operators.Sms;

/**
 * @author Fernando Franco Giráldez
 */
public abstract class SmsRenderer extends TelephonyEventRenderer {

    @Override
    public void render() {
        Sms sms = (Sms) getContent();
        text.setText(
                String.format(
                        "%s \n%s at %s %s",
                        sms.getText(),
                        (sms.isIncoming() ? "Received from " : "Sent to ") + sms.getPartner(),
                        dateFormat.format(sms.getTimestamp()),
                        timeFormat.format(sms.getTimestamp())
                )
        );
    }
}
