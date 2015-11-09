package es.ffgiraldez.reactiveyourandroid.operators;

import com.pedrogomez.renderers.RendererAdapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import es.ffgiraldez.reactiveyourandroid.operators.renderers.TelephonyEventRendererAdapter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Task to execute during demo
 * - Filter TelephonyEvent to show only from a specific partner
 * - Filter to show only Sms
 */
public class MainActivity extends AppCompatActivity {

    private ListView list;
    private RendererAdapter<TelephonyEvent> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeComponents();

        Observable<TelephonyEvent> realTimeSequence1 = Observable
                .interval(3, TimeUnit.SECONDS)
                .map(i ->
                                new Call(
                                        new Date(),
                                        "John",
                                        i,
                                        true
                                )
                );

        Observable<TelephonyEvent> realTimeSequence2 = Observable
                .interval(4, TimeUnit.SECONDS)
                .map(i ->
                                new Sms(
                                        new Date(),
                                        "John",
                                        String.format("Dude sent you %d sms", i),
                                        true
                                )
                );

        Observable<TelephonyEvent> realTimeSequence3 = Observable
                .interval(5, TimeUnit.SECONDS)
                .map(i ->
                                new Sms(
                                        new Date(),
                                        "Eric",
                                        String.format("Yo, Eric?  %d sms", i),
                                        false
                                )
                );

        Observable<TelephonyEvent> oldEvents = Observable.from(existingTelephonyEvents());
        oldEvents
                .mergeWith(realTimeSequence1)
                .mergeWith(realTimeSequence2)
                .mergeWith(realTimeSequence3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addSms);
    }

    private Iterable<TelephonyEvent> existingTelephonyEvents() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -4);
        return Arrays.asList(
                new Call(
                        addDays(-5),
                        "John",
                        5,
                        true
                ),
                new Sms(
                        addDays(-4),
                        "John",
                        "Going to GDG AndaTour 2015?",
                        true
                ),
                new Call(
                        addDays(-3),
                        "Eric",
                        5,
                        false
                ),
                new Sms(
                        addDays(-2),
                        "Eric",
                        "Eric, going to AndaTour with John",
                        false
                ),
                new Sms(
                        addDays(-1),
                        "John",
                        "Sure, I'll meet you there",
                        false
                )
        );
    }

    private void addSms(TelephonyEvent telephonyEvent) {
        adapter.add(telephonyEvent);
        adapter.notifyDataSetChanged();
    }

    private void initializeComponents() {
        setContentView(R.layout.activity_main);
        adapter = new TelephonyEventRendererAdapter(LayoutInflater.from(this));
        list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(adapter);
    }

    private Date addDays(long toAdd) {
        return new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(toAdd));
    }
}
