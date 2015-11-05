package es.ffgiraldez.reactiveyourandroid.operators.renderers;

import com.pedrogomez.renderers.Renderer;

import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;

import es.ffgiraldez.reactiveyourandroid.operators.R;
import es.ffgiraldez.reactiveyourandroid.operators.Sms;

/**
 * @author Fernando Franco Giráldez
 */
public abstract class TelephonyEventRenderer extends Renderer<Sms> {
    private TextView text;
    private final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
    private final DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

    @Override
    protected void setUpView(View rootView) {
        text = (TextView) rootView.findViewById(R.id.sms_text);
    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @Override
    public void render() {
        Sms sms = getContent();
        text.setText(
                String.format(
                        "%s \n%s at %s %s",
                        sms.getText(),
                        sms.isIncoming() ? "Received" : "Sent",
                        dateFormat.format(sms.getTimestamp()),
                        timeFormat.format(sms.getTimestamp())
                )
        );
    }
}
