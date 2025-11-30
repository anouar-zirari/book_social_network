package com.anwardev.booksocialnetwork.service.mapper;

import static com.anwardev.booksocialnetwork.domain.ReviewAsserts.*;
import static com.anwardev.booksocialnetwork.domain.ReviewTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReviewMapperTest {

    private ReviewMapper reviewMapper;

    @BeforeEach
    void setUp() {
        reviewMapper = new ReviewMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getReviewSample1();
        var actual = reviewMapper.toEntity(reviewMapper.toDto(expected));
        assertReviewAllPropertiesEquals(expected, actual);
    }
}
