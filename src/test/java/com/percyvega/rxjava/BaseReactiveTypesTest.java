package com.percyvega.rxjava;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseReactiveTypesTest {

    private static final Logger log = LoggerFactory.getLogger(BaseReactiveTypesTest.class);

    @Test
    public void single_just() {
        Single.just(1)
                .map(i -> i * 10)
                .map(Object::toString)
                .subscribe((Consumer<String>) log::info);
    }

    @Test
    public void maybe_just() {
        Maybe.just("something")
                .subscribe(log::info);
    }

    @Test
    public void maybe_never() {
        Maybe.never()
                .subscribe(o -> log.info("Something is here..."));
    }

    @Test
    public  void completable_complete() {
        Completable.complete()
                .subscribe(() -> log.info("Completed"));
    }

    @Test
    public void flowable_just() {
        Flowable.just("foo", "bar", "baz")
                .filter(s -> s.startsWith("b"))
                .map(String::toUpperCase)
                .subscribe(log::info);
    }
}
