package com.anwardev.booksocialnetwork.domain;

import static com.anwardev.booksocialnetwork.domain.BooktestingTestSamples.*;
import static com.anwardev.booksocialnetwork.domain.ReviewTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.anwardev.booksocialnetwork.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReviewTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Review.class);
        Review review1 = getReviewSample1();
        Review review2 = new Review();
        assertThat(review1).isNotEqualTo(review2);

        review2.setId(review1.getId());
        assertThat(review1).isEqualTo(review2);

        review2 = getReviewSample2();
        assertThat(review1).isNotEqualTo(review2);
    }

    @Test
    void manytooneTest() {
        Review review = getReviewRandomSampleGenerator();
        Booktesting booktestingBack = getBooktestingRandomSampleGenerator();

        review.setManytoone(booktestingBack);
        assertThat(review.getManytoone()).isEqualTo(booktestingBack);

        review.manytoone(null);
        assertThat(review.getManytoone()).isNull();
    }
}
