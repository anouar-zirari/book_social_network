package com.anwardev.booksocialnetwork.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class BooktestingTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Booktesting getBooktestingSample1() {
        return new Booktesting().id(1L).name("name1").lastname("lastname1").email("email1");
    }

    public static Booktesting getBooktestingSample2() {
        return new Booktesting().id(2L).name("name2").lastname("lastname2").email("email2");
    }

    public static Booktesting getBooktestingRandomSampleGenerator() {
        return new Booktesting()
            .id(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .lastname(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString());
    }
}
