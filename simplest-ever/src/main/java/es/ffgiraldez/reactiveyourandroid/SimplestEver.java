package es.ffgiraldez.reactiveyourandroid;

import java.util.Scanner;

import rx.Observable;
import rx.Subscriber;

@SuppressWarnings("Convert2Lambda")
public class SimplestEver {
    private static final Scanner KEYBOARD = new Scanner(System.in);

    private static class MySequenceOfNumbers extends Observable<Integer> {

        public MySequenceOfNumbers() {
            super(new OnSubscribe<Integer>() {
                @Override
                public void call(Subscriber<? super Integer> subscriber) {
                    subscriber.onNext(1);
                    subscriber.onNext(2);
                    subscriber.onNext(3);
                    subscriber.onCompleted();
                }
            });
        }
    }

    private static class MyConsoleSubscriber extends Subscriber<Integer> {
        @Override
        public void onNext(Integer value) {
            System.out.println(String.format("Received value %d", value));
        }

        @Override
        public void onError(Throwable error) {
            System.out.println(String.format("Sequence faulted with %s", error));
        }

        @Override
        public void onCompleted() {
            System.out.println("Sequence terminated");
        }
    }

    public static void main(String[] args) {

        Observable<Integer> numbers = new MySequenceOfNumbers();
        Subscriber<Integer> subscriber = new MyConsoleSubscriber();

        System.out.println("Press any key to subscribe");
        KEYBOARD.nextLine();

        numbers.subscribe(subscriber);
        System.out.println("Press any key to exit");
        KEYBOARD.nextLine();
    }
}
