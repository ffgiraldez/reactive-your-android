package es.ffgiraldez.reactiveyourandroid.operators.renderers;

import com.pedrogomez.renderers.RendererAdapter;

import android.view.LayoutInflater;

import es.ffgiraldez.reactiveyourandroid.operators.Sms;

/**
 * @author Fernando Franco Giráldez
 */
public class TelephonyEventRendererAdapter extends RendererAdapter<Sms> {
    public TelephonyEventRendererAdapter(LayoutInflater layoutInflater) {
        super(layoutInflater, TelephonyEventRendererBuilder.build(), new TelephonyEventCollection());
    }
}
