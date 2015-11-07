package es.ffgiraldez.reactiveyourandroid.simplelambda;

import java.util.Scanner;

import rx.Observable;

public class SimplestEverLambdas {
    private static final Scanner KEYBOARD = new Scanner(System.in);

    public static void main(String[] args) {
        Observable<Integer> numbers = Observable.create(subscriber -> {
            subscriber.onNext(1);
            subscriber.onNext(2);
            subscriber.onNext(3);
            subscriber.onCompleted();
        });

        System.out.println("Press any key to subscribe");
        KEYBOARD.nextLine();

        numbers.subscribe(
                value -> System.out.println(String.format("Received value %d", value)),
                error -> System.out.println(String.format("Sequence faulted with %s", error)),
                () -> System.out.println("Sequence terminated")
        );
        System.out.println("Press any key to exit");
        KEYBOARD.nextLine();
    }
}
