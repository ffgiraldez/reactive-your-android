package es.ffgiraldez.reactiveyourandroid;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class EventsAreClunky {

    public static void main(String[] args) {
        Bus bus = new Bus(ThreadEnforcer.ANY);
        EventSource source = new EventSource(bus);
        EventConsumer consumer = new EventConsumer(bus);
        source.start();
    }
}
