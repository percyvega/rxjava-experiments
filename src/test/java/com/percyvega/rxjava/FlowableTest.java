package com.percyvega.rxjava;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class FlowableTest {

    private static final Logger logger = LoggerFactory.getLogger(FlowableTest.class);

    @Test
    public void test() throws InterruptedException {

        Flowable<String> intervals = Flowable
                .interval(100, TimeUnit.MILLISECONDS, Schedulers.computation())
                .limit(10)
                .map(tick -> "Tick #" + tick)
                .subscribeOn(Schedulers.computation());

        Flowable<String> strings = Flowable
                .just("abc", "def", "ghi", "jkl")
                .subscribeOn(Schedulers.computation());

        Flowable<Object> uuids = Flowable
                .generate(emitter -> emitter.onNext(UUID.randomUUID()))
                .limit(10)
                .subscribeOn(Schedulers.computation());

        Flowable.merge(strings, intervals, uuids)
                .subscribe(obj -> logger.info("Received: {}", obj));

        Thread.sleep(3000);

        logger.info("==================");

        Flowable.zip(intervals, uuids, strings,
                (i, u, s) -> String.format("%s {%s} -> %s", i, u, s))
                .subscribe(obj -> logger.info("Received: {}", obj));

        Thread.sleep(3000);

        logger.info("==================");
    }
}
