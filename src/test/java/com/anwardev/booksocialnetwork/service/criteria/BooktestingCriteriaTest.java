package com.anwardev.booksocialnetwork.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class BooktestingCriteriaTest {

    @Test
    void newBooktestingCriteriaHasAllFiltersNullTest() {
        var booktestingCriteria = new BooktestingCriteria();
        assertThat(booktestingCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void booktestingCriteriaFluentMethodsCreatesFiltersTest() {
        var booktestingCriteria = new BooktestingCriteria();

        setAllFilters(booktestingCriteria);

        assertThat(booktestingCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void booktestingCriteriaCopyCreatesNullFilterTest() {
        var booktestingCriteria = new BooktestingCriteria();
        var copy = booktestingCriteria.copy();

        assertThat(booktestingCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(booktestingCriteria)
        );
    }

    @Test
    void booktestingCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var booktestingCriteria = new BooktestingCriteria();
        setAllFilters(booktestingCriteria);

        var copy = booktestingCriteria.copy();

        assertThat(booktestingCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(booktestingCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var booktestingCriteria = new BooktestingCriteria();

        assertThat(booktestingCriteria).hasToString("BooktestingCriteria{}");
    }

    private static void setAllFilters(BooktestingCriteria booktestingCriteria) {
        booktestingCriteria.id();
        booktestingCriteria.name();
        booktestingCriteria.lastname();
        booktestingCriteria.email();
        booktestingCriteria.somthingId();
        booktestingCriteria.distinct();
    }

    private static Condition<BooktestingCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getName()) &&
                condition.apply(criteria.getLastname()) &&
                condition.apply(criteria.getEmail()) &&
                condition.apply(criteria.getSomthingId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<BooktestingCriteria> copyFiltersAre(BooktestingCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getName(), copy.getName()) &&
                condition.apply(criteria.getLastname(), copy.getLastname()) &&
                condition.apply(criteria.getEmail(), copy.getEmail()) &&
                condition.apply(criteria.getSomthingId(), copy.getSomthingId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
