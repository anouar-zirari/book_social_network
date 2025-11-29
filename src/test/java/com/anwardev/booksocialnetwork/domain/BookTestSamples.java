package com.anwardev.booksocialnetwork.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class BookTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Book getBookSample1() {
        return new Book().id(1L).author("author1").aboutAuthor("aboutAuthor1").title("title1").category("category1").isbn("isbn1");
    }

    public static Book getBookSample2() {
        return new Book().id(2L).author("author2").aboutAuthor("aboutAuthor2").title("title2").category("category2").isbn("isbn2");
    }

    public static Book getBookRandomSampleGenerator() {
        return new Book()
            .id(longCount.incrementAndGet())
            .author(UUID.randomUUID().toString())
            .aboutAuthor(UUID.randomUUID().toString())
            .title(UUID.randomUUID().toString())
            .category(UUID.randomUUID().toString())
            .isbn(UUID.randomUUID().toString());
    }
}
