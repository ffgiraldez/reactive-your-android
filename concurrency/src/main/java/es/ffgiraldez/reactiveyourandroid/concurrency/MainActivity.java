package es.ffgiraldez.reactiveyourandroid.concurrency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Concurrency";
    public static final int SLEEP_TIME = 2000;

    private Subscription subscription;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeComponents();

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Log.d(TAG, String.format("Invoked on ThreadId %d", Thread.currentThread().getId()));
                    Log.d(TAG, String.format("Sending 1 on ThreadId %d", Thread.currentThread().getId()));
                    subscriber.onNext("1");
                    Thread.sleep(SLEEP_TIME);
                    Log.d(TAG, String.format("Sending 2 on ThreadId %d", Thread.currentThread().getId()));
                    subscriber.onNext("2");
                    Thread.sleep(SLEEP_TIME);
                    Log.d(TAG, String.format("Sending 3 on ThreadId %d", Thread.currentThread().getId()));
                    subscriber.onNext("3");
                    Thread.sleep(SLEEP_TIME);
                    Log.d(TAG, String.format("Finished on ThreadId %d", Thread.currentThread().getId()));
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
            }
        });

        subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        value -> addText(
                                String.format("Received %s on ThreadId: %d", value, Thread.currentThread().getId())
                        ),
                        error -> addText(String.format("OnError on threadId: %d", Thread.currentThread().getId())),
                        () -> addText(String.format("OnComplete on threadId: %d", Thread.currentThread().getId()))
                );
    }

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
        super.onDestroy();
    }

    private void initializeComponents() {
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
    }

    private void addText(String toAdd) {
        text.setText(text.getText() + "\n" + toAdd);
    }
}
