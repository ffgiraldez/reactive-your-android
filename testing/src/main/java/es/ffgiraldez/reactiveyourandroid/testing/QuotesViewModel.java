package es.ffgiraldez.reactiveyourandroid.testing;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Fernando Franco Giráldez
 */
public class QuotesViewModel {
    private final QuoteService service;
    private final SchedulerProvider schedulerProvider;
    private int quote;
    private boolean timeout;

    public QuotesViewModel(QuoteService service, SchedulerProvider schedulerProvider) {
        this.service = service;
        this.schedulerProvider = schedulerProvider;
    }

    public void startGetQuote(String symbol) {
        setTimeout(false);

        service.getQuote(symbol)
                .subscribeOn(schedulerProvider.newThread())
                .observeOn(schedulerProvider.dispatcher())
                .timeout(10, TimeUnit.SECONDS, schedulerProvider.poolThread())
                .subscribe(
                        this::setQuote,
                        ex -> {
                            if (ex instanceof TimeoutException) {
                                setTimeout(true);
                            }
                        }
                );
    }

    public boolean isTimeout() {
        return timeout;
    }

    public void setTimeout(boolean timeout) {
        this.timeout = timeout;
    }

    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }
}
