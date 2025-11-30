package com.anwardev.booksocialnetwork.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.anwardev.booksocialnetwork.domain.Review} entity. This class is used
 * in {@link com.anwardev.booksocialnetwork.web.rest.ReviewResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /reviews?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReviewCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter opinion;

    private StringFilter rating;

    private LongFilter manytooneId;

    private LongFilter userId;

    private Boolean distinct;

    public ReviewCriteria() {}

    public ReviewCriteria(ReviewCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.opinion = other.optionalOpinion().map(StringFilter::copy).orElse(null);
        this.rating = other.optionalRating().map(StringFilter::copy).orElse(null);
        this.manytooneId = other.optionalManytooneId().map(LongFilter::copy).orElse(null);
        this.userId = other.optionalUserId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public ReviewCriteria copy() {
        return new ReviewCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOpinion() {
        return opinion;
    }

    public Optional<StringFilter> optionalOpinion() {
        return Optional.ofNullable(opinion);
    }

    public StringFilter opinion() {
        if (opinion == null) {
            setOpinion(new StringFilter());
        }
        return opinion;
    }

    public void setOpinion(StringFilter opinion) {
        this.opinion = opinion;
    }

    public StringFilter getRating() {
        return rating;
    }

    public Optional<StringFilter> optionalRating() {
        return Optional.ofNullable(rating);
    }

    public StringFilter rating() {
        if (rating == null) {
            setRating(new StringFilter());
        }
        return rating;
    }

    public void setRating(StringFilter rating) {
        this.rating = rating;
    }

    public LongFilter getManytooneId() {
        return manytooneId;
    }

    public Optional<LongFilter> optionalManytooneId() {
        return Optional.ofNullable(manytooneId);
    }

    public LongFilter manytooneId() {
        if (manytooneId == null) {
            setManytooneId(new LongFilter());
        }
        return manytooneId;
    }

    public void setManytooneId(LongFilter manytooneId) {
        this.manytooneId = manytooneId;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public Optional<LongFilter> optionalUserId() {
        return Optional.ofNullable(userId);
    }

    public LongFilter userId() {
        if (userId == null) {
            setUserId(new LongFilter());
        }
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ReviewCriteria that = (ReviewCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(opinion, that.opinion) &&
            Objects.equals(rating, that.rating) &&
            Objects.equals(manytooneId, that.manytooneId) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, opinion, rating, manytooneId, userId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReviewCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalOpinion().map(f -> "opinion=" + f + ", ").orElse("") +
            optionalRating().map(f -> "rating=" + f + ", ").orElse("") +
            optionalManytooneId().map(f -> "manytooneId=" + f + ", ").orElse("") +
            optionalUserId().map(f -> "userId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
