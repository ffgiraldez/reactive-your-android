package es.ffgiraldez.reactiveyourandroid;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * @author Fernando Franco Giráldez
 */
class EventConsumer {
    public EventConsumer(Bus bus) {
        bus.register(this);
    }

    @Subscribe
    public void consoleKeyPresedHandler(ConsoleKeyPressedEvent event) {
        System.out.println(event.getCharacter());
    }
}
