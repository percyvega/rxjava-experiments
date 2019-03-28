package com.percyvega.rxjava;

import io.reactivex.Observable;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ObservableCreateTest {

    private static final Logger logger = LoggerFactory.getLogger(ObservableCreateTest.class);

    @Test
    public void observable_create_subscribe_retry() {
        List<String> torah = Arrays.asList("Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy");
        Random random = new Random();

        Observable<String> source = Observable.create(subscriber -> {
            for (String s : torah) {
                Thread.sleep(1000);
                if (random.nextInt(6) == 0) {
                    subscriber.onError(new RuntimeException("Bad luck for you..." + s));
                }
                subscriber.onNext(s);
            }
            subscriber.onComplete();
        });

        for (int i = 0; i < 3; i++) {
            logger.info("--------------------------------------------------");
            final int ii = i;
            source.subscribe(
                    next -> logger.info("{} Next: {}", ii, next),
                    error -> logger.error("{} Whoops", ii),
                    () -> logger.info("{} Done", ii));
        }

        logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++");
        source
                .retry(5)
                .subscribe(next -> logger.info("Next: {}", next),
                        error -> logger.error("Whoops"),
                        () -> logger.info("Done"));
    }
}
