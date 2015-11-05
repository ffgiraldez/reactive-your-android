package es.ffgiraldez.reactiveyourandroid.operators.renderers;

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Arrays;
import java.util.Collection;

import es.ffgiraldez.reactiveyourandroid.operators.Sms;

/**
 * @author Fernando Franco Giráldez
 */
public class TelephonyEventRendererBuilder extends RendererBuilder<Sms> {
    public static TelephonyEventRendererBuilder build() {
        return new TelephonyEventRendererBuilder(
                Arrays.asList(
                        new OutgoingSmsRenderer(),
                        new IncomingSmsRenderer()
                )
        );
    }

    private TelephonyEventRendererBuilder(
            Collection<Renderer<Sms>> prototypes) {
        super(prototypes);
    }

    @Override
    protected Class getPrototypeClass(Sms content) {
        return content.isIncoming() ? IncomingSmsRenderer.class : OutgoingSmsRenderer.class;
    }
}
