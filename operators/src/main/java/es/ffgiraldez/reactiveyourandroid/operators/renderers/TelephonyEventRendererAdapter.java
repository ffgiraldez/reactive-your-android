package es.ffgiraldez.reactiveyourandroid.operators.renderers;

import com.pedrogomez.renderers.RendererAdapter;

import android.view.LayoutInflater;

import es.ffgiraldez.reactiveyourandroid.operators.TelephonyEvent;

/**
 * @author Fernando Franco Giráldez
 */
public class TelephonyEventRendererAdapter extends RendererAdapter<TelephonyEvent> {
    public TelephonyEventRendererAdapter(LayoutInflater layoutInflater) {
        super(layoutInflater, TelephonyEventRendererBuilder.build(), new TelephonyEventCollection());
    }
}
