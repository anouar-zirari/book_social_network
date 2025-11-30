package com.anwardev.booksocialnetwork.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.anwardev.booksocialnetwork.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BooktestingDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BooktestingDTO.class);
        BooktestingDTO booktestingDTO1 = new BooktestingDTO();
        booktestingDTO1.setId(1L);
        BooktestingDTO booktestingDTO2 = new BooktestingDTO();
        assertThat(booktestingDTO1).isNotEqualTo(booktestingDTO2);
        booktestingDTO2.setId(booktestingDTO1.getId());
        assertThat(booktestingDTO1).isEqualTo(booktestingDTO2);
        booktestingDTO2.setId(2L);
        assertThat(booktestingDTO1).isNotEqualTo(booktestingDTO2);
        booktestingDTO1.setId(null);
        assertThat(booktestingDTO1).isNotEqualTo(booktestingDTO2);
    }
}
