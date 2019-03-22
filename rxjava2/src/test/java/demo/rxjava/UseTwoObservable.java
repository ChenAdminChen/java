package demo.rxjava;

import io.reactivex.Observable;
import org.junit.Test;

public class UseTwoObservable {
    @Test
    public void testTwoObservable() {
        Observable.range(2, 10)
                .flatMap(r -> {
                    return Observable.range(0,r);
                })
                .subscribe(r -> {
                    System.out.println(r);
                });

    }
}
