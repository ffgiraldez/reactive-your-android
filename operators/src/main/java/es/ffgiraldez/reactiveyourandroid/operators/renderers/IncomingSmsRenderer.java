package es.ffgiraldez.reactiveyourandroid.operators.renderers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.ffgiraldez.reactiveyourandroid.operators.R;

/**
 * @author Fernando Franco Giráldez
 */
public class IncomingSmsRenderer extends TelephonyEventRenderer {

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.view_incoming_sms, parent, false);
    }
}
