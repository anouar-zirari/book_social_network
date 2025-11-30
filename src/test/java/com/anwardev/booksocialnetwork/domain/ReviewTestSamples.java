package com.anwardev.booksocialnetwork.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ReviewTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Review getReviewSample1() {
        return new Review().id(1L).opinion("opinion1").rating("rating1");
    }

    public static Review getReviewSample2() {
        return new Review().id(2L).opinion("opinion2").rating("rating2");
    }

    public static Review getReviewRandomSampleGenerator() {
        return new Review().id(longCount.incrementAndGet()).opinion(UUID.randomUUID().toString()).rating(UUID.randomUUID().toString());
    }
}
