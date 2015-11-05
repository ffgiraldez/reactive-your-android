package es.ffgiraldez.reactiveyourandroid;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

import java.util.Scanner;

public class EventsAreClunky {

    private static final Scanner KEYBOARD = new Scanner(System.in);

    private static class ConsoleKeyPressedEvent {

        private final String character;

        public ConsoleKeyPressedEvent(String character) {
            this.character = character;
        }

        public String getCharacter() {
            return character;
        }
    }

    private static class EventSource {
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

    private static class EventConsumer {
        public EventConsumer(Bus bus) {
            bus.register(this);
        }

        @Subscribe
        public void consoleKeyPresedHandler(ConsoleKeyPressedEvent event) {
            System.out.println(event.getCharacter());
        }
    }

    public static void main(String[] args) {
        Bus bus = new Bus(ThreadEnforcer.ANY);
        EventSource source = new EventSource(bus);
        EventConsumer consumer = new EventConsumer(bus);
        source.start();
    }
}
