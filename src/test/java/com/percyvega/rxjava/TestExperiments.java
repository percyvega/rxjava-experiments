package com.percyvega.rxjava;

import org.junit.Test;
import rx.Observable;
import rx.observables.MathObservable;

public class TestExperiments {

    @Test
    public void just() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);

        observable
                .subscribe(
                System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("No more data")
        );
    }

    @Test
    public void range() {
        Observable<Integer> observable = Observable.range(0, 20);

        observable.subscribe(
                System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("No more data")
        );
    }

    @Test
    public void double_range() {
        Observable<Integer> observable = Observable.range(0, 20);
        Observable<Integer> observable2 = Observable.range(100, 20);

        observable
                .map(iInteger -> iInteger + 50)
                .zipWith(observable2, (iInteger, iInteger2) -> iInteger.toString() + ", " + iInteger2.toString())
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("No more data")
                );
    }

    @Test
    public void math_observable() {
        Observable<Integer> observable = Observable.range(1, 20);

        observable
                .window(10)
                .flatMap(MathObservable::averageInteger)
                .map(average -> "{'average': " + average + "}")
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace
                );
    }

}
