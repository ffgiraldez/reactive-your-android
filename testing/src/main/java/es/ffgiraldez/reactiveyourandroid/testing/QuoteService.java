package es.ffgiraldez.reactiveyourandroid.testing;

import rx.Observable;

public interface QuoteService {
    Observable<Integer> getQuote(String symbol);
}
