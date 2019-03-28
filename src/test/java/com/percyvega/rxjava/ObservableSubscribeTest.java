package com.percyvega.rxjava;

import io.reactivex.Observable;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObservableSubscribeTest {

    private static final Logger log = LoggerFactory.getLogger(ObservableSubscribeTest.class);

    @Test
    public void observable_just() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);

        observable
                .subscribe(
                        o -> log.info(String.valueOf(o)),
                        Throwable::printStackTrace,
                        () -> System.out.println("No more data")
                );
    }

    @Test
    public void observable_range() {
        Observable<Integer> observable = Observable.range(5, 8);

        observable
                .subscribe(
                    o -> log.info(String.valueOf(o)),
                    Throwable::printStackTrace,
                    () -> System.out.println("No more data")
        );
    }

    @Test
    public void observable_range_zipWith() {
        Observable<Integer> observable = Observable.range(10, 20);
        Observable<Integer> observable2 = Observable.range(50, 70);

        observable
                .map(iInteger -> iInteger + 100)
                .zipWith(observable2, (iInteger, iInteger2) -> iInteger.toString() + ", " + iInteger2.toString())
                .subscribe(
                        o -> log.info(String.valueOf(o)),
                        Throwable::printStackTrace,
                        () -> System.out.println("No more data")
                );
    }

}
