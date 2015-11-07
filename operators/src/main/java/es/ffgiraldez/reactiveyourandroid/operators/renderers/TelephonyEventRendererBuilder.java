package es.ffgiraldez.reactiveyourandroid.operators.renderers;

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Arrays;
import java.util.Collection;

import es.ffgiraldez.reactiveyourandroid.operators.Sms;
import es.ffgiraldez.reactiveyourandroid.operators.TelephonyEvent;

/**
 * @author Fernando Franco Giráldez
 */
public class TelephonyEventRendererBuilder extends RendererBuilder<TelephonyEvent> {
    public static TelephonyEventRendererBuilder build() {
        return new TelephonyEventRendererBuilder(
                Arrays.asList(
                        new OutgoingSmsRenderer(),
                        new OutgoingCallRenderer(),
                        new IncomingSmsRenderer(),
                        new IncomingCallRenderer()
                )
        );
    }

    private TelephonyEventRendererBuilder(
            Collection<Renderer<TelephonyEvent>> prototypes) {
        super(prototypes);
    }

    @Override
    protected Class getPrototypeClass(TelephonyEvent content) {
        if (content instanceof Sms) {
            return content.isIncoming() ? IncomingSmsRenderer.class : OutgoingSmsRenderer.class;
        }
        return content.isIncoming() ? IncomingCallRenderer.class : OutgoingCallRenderer.class;
    }
}
