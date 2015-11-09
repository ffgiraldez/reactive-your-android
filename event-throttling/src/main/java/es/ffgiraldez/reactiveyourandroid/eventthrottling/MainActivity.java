package es.ffgiraldez.reactiveyourandroid.eventthrottling;

import com.jakewharton.rxbinding.widget.RxTextView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    private CompositeSubscription subscription = new CompositeSubscription();
    private Observable<String> textChangeObservable;
    private EditText editTextUnthrottled;
    private EditText editTextThrottled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        subscription.add(
                textChangeObservable
                        .subscribe(editTextUnthrottled::setText)
        );
        subscription.add(
                textChangeObservable
                        .throttleLast(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(editTextThrottled::setText)
        );
    }

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
        super.onDestroy();
    }

    private void initializeComponents() {
        editTextThrottled = (EditText) findViewById(R.id.edit_throttled);
        editTextUnthrottled = (EditText) findViewById(R.id.edit_unthrottled);
        textChangeObservable = RxTextView
                .textChangeEvents((TextView) findViewById(R.id.edit_text))
                .map(event -> event.text().toString());
    }
}
