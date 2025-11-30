package com.anwardev.booksocialnetwork.service.mapper;

import static com.anwardev.booksocialnetwork.domain.BooktestingAsserts.*;
import static com.anwardev.booksocialnetwork.domain.BooktestingTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BooktestingMapperTest {

    private BooktestingMapper booktestingMapper;

    @BeforeEach
    void setUp() {
        booktestingMapper = new BooktestingMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getBooktestingSample1();
        var actual = booktestingMapper.toEntity(booktestingMapper.toDto(expected));
        assertBooktestingAllPropertiesEquals(expected, actual);
    }
}
