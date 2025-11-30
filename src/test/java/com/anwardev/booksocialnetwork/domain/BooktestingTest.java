package com.anwardev.booksocialnetwork.domain;

import static com.anwardev.booksocialnetwork.domain.BooktestingTestSamples.*;
import static com.anwardev.booksocialnetwork.domain.ReviewTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.anwardev.booksocialnetwork.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class BooktestingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Booktesting.class);
        Booktesting booktesting1 = getBooktestingSample1();
        Booktesting booktesting2 = new Booktesting();
        assertThat(booktesting1).isNotEqualTo(booktesting2);

        booktesting2.setId(booktesting1.getId());
        assertThat(booktesting1).isEqualTo(booktesting2);

        booktesting2 = getBooktestingSample2();
        assertThat(booktesting1).isNotEqualTo(booktesting2);
    }

    @Test
    void somthingTest() {
        Booktesting booktesting = getBooktestingRandomSampleGenerator();
        Review reviewBack = getReviewRandomSampleGenerator();

        booktesting.addSomthing(reviewBack);
        assertThat(booktesting.getSomthings()).containsOnly(reviewBack);
        assertThat(reviewBack.getManytoone()).isEqualTo(booktesting);

        booktesting.removeSomthing(reviewBack);
        assertThat(booktesting.getSomthings()).doesNotContain(reviewBack);
        assertThat(reviewBack.getManytoone()).isNull();

        booktesting.somthings(new HashSet<>(Set.of(reviewBack)));
        assertThat(booktesting.getSomthings()).containsOnly(reviewBack);
        assertThat(reviewBack.getManytoone()).isEqualTo(booktesting);

        booktesting.setSomthings(new HashSet<>());
        assertThat(booktesting.getSomthings()).doesNotContain(reviewBack);
        assertThat(reviewBack.getManytoone()).isNull();
    }
}
