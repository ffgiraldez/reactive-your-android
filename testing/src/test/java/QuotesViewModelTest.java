import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import es.ffgiraldez.reactiveyourandroid.testing.QuoteService;
import es.ffgiraldez.reactiveyourandroid.testing.QuotesViewModel;
import es.ffgiraldez.reactiveyourandroid.testing.SchedulerProvider;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;
import rx.subjects.PublishSubject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

/**
 * @author Fernando Franco Giráldez
 */
public class QuotesViewModelTest {

    private static final Integer EXPECTED_QUOTE = 105;
    private static final int TIMEOUT = 10;
    @Mock
    QuoteService service;
    @Mock
    SchedulerProvider provider;

    QuotesViewModel viewModel;
    PublishSubject<Integer> subject;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        viewModel = new QuotesViewModel(service, provider);
        subject = PublishSubject.create();
        given(service.getQuote(anyString())).willReturn(subject);
    }

    @Test
    public void shouldViewModelSetQuoteWhenReturnedByService() {
        TestScheduler dispatcher = new TestScheduler();
        TestScheduler newThread = new TestScheduler();
        Scheduler poolThread = Schedulers.io();

        given(provider.dispatcher()).willReturn(dispatcher);
        given(provider.newThread()).willReturn(newThread);
        given(provider.poolThread()).willReturn(poolThread);
        PublishSubject<Integer> subject = PublishSubject.create();
        given(service.getQuote(anyString())).willReturn(subject);

        viewModel.startGetQuote("msft");
        assertThat(viewModel.getQuote()).isEqualTo(0);

        newThread.createWorker().schedule(() -> subject.onNext(EXPECTED_QUOTE));
        newThread.triggerActions();
        assertThat(viewModel.getQuote()).isEqualTo(0);

        dispatcher.triggerActions();
        assertThat(viewModel.getQuote()).isEqualTo(EXPECTED_QUOTE);
    }

    @Test
    public void shouldViewModelSetQuoteWhenReturnedByServiceUsingInmediateSchedulersa() {
        Scheduler dispatcher = Schedulers.immediate();
        Scheduler newThread = Schedulers.immediate();
        Scheduler poolThread = Schedulers.io();

        given(provider.dispatcher()).willReturn(dispatcher);
        given(provider.newThread()).willReturn(newThread);
        given(provider.poolThread()).willReturn(poolThread);

        viewModel.startGetQuote("msft");
        assertThat(viewModel.getQuote()).isEqualTo(0);

        newThread.createWorker().schedule(() -> subject.onNext(EXPECTED_QUOTE));
        assertThat(viewModel.getQuote()).isEqualTo(EXPECTED_QUOTE);
    }

    @Test
    public void shouldViewModelSetTimeoutWhenServiceTimeout() {

        TestScheduler dispatcher = new TestScheduler();
        TestScheduler newThread = new TestScheduler();
        TestScheduler poolThread = new TestScheduler();

        given(provider.dispatcher()).willReturn(dispatcher);
        given(provider.newThread()).willReturn(newThread);
        given(provider.poolThread()).willReturn(poolThread);

        viewModel.startGetQuote("msft");
        assertThat(viewModel.isTimeout()).isFalse();

        poolThread.advanceTimeBy(TIMEOUT - 1, TimeUnit.SECONDS);
        assertThat(viewModel.isTimeout()).isFalse();

        poolThread.advanceTimeBy(1, TimeUnit.SECONDS);
        assertThat(viewModel.isTimeout()).isTrue();
    }
}