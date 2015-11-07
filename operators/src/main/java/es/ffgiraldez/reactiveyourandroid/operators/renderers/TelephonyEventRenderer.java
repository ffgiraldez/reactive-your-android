package es.ffgiraldez.reactiveyourandroid.operators.renderers;

import com.pedrogomez.renderers.Renderer;

import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;

import es.ffgiraldez.reactiveyourandroid.operators.R;
import es.ffgiraldez.reactiveyourandroid.operators.TelephonyEvent;

/**
 * @author Fernando Franco Giráldez
 */
public abstract class TelephonyEventRenderer extends Renderer<TelephonyEvent> {
    TextView text;
    final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
    final DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

    @Override
    protected void setUpView(View rootView) {
        text = (TextView) rootView.findViewById(R.id.telephony_text);
    }

    @Override
    protected void hookListeners(View rootView) {

    }
}
