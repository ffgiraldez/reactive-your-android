package es.ffgiraldez.reactiveyourandroid;

import com.squareup.otto.Bus;

import java.util.Scanner;

/**
 * @author Fernando Franco Giráldez
 */
class EventSource {
    private static final Scanner KEYBOARD = new Scanner(System.in);
    private final Bus bus;

    public EventSource(Bus bus) {
        this.bus = bus;
    }

    public void start() {
        while (true) {
            String character = KEYBOARD.next();
            bus.post(new ConsoleKeyPressedEvent(character));
        }
    }
}
