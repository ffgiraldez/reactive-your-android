package es.ffgiraldez.reactiveyourandroid.testing;

import rx.Scheduler;

/**
 * @author Fernando Franco Giráldez
 */
public interface SchedulerProvider {

    Scheduler newThread();

    Scheduler dispatcher();

    Scheduler poolThread();
}
