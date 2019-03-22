package demo.rxjava;

import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class FilteringObservable {

    @Test
    public void testDebounce() throws InterruptedException {
        Observable.range(0, 15)
                .debounce(1, TimeUnit.SECONDS)
                .subscribe(r -> {
                    System.out.println(r);
                });

        Thread.sleep(2000);
    }
}
