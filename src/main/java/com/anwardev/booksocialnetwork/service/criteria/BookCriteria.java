package com.anwardev.booksocialnetwork.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.anwardev.booksocialnetwork.domain.Book} entity. This class is used
 * in {@link com.anwardev.booksocialnetwork.web.rest.BookResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /books?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BookCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter author;

    private StringFilter aboutAuthor;

    private StringFilter title;

    private StringFilter category;

    private LocalDateFilter publishDate;

    private FloatFilter price;

    private StringFilter isbn;

    private Boolean distinct;

    public BookCriteria() {}

    public BookCriteria(BookCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.author = other.optionalAuthor().map(StringFilter::copy).orElse(null);
        this.aboutAuthor = other.optionalAboutAuthor().map(StringFilter::copy).orElse(null);
        this.title = other.optionalTitle().map(StringFilter::copy).orElse(null);
        this.category = other.optionalCategory().map(StringFilter::copy).orElse(null);
        this.publishDate = other.optionalPublishDate().map(LocalDateFilter::copy).orElse(null);
        this.price = other.optionalPrice().map(FloatFilter::copy).orElse(null);
        this.isbn = other.optionalIsbn().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public BookCriteria copy() {
        return new BookCriteria(this);
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

    public StringFilter getAuthor() {
        return author;
    }

    public Optional<StringFilter> optionalAuthor() {
        return Optional.ofNullable(author);
    }

    public StringFilter author() {
        if (author == null) {
            setAuthor(new StringFilter());
        }
        return author;
    }

    public void setAuthor(StringFilter author) {
        this.author = author;
    }

    public StringFilter getAboutAuthor() {
        return aboutAuthor;
    }

    public Optional<StringFilter> optionalAboutAuthor() {
        return Optional.ofNullable(aboutAuthor);
    }

    public StringFilter aboutAuthor() {
        if (aboutAuthor == null) {
            setAboutAuthor(new StringFilter());
        }
        return aboutAuthor;
    }

    public void setAboutAuthor(StringFilter aboutAuthor) {
        this.aboutAuthor = aboutAuthor;
    }

    public StringFilter getTitle() {
        return title;
    }

    public Optional<StringFilter> optionalTitle() {
        return Optional.ofNullable(title);
    }

    public StringFilter title() {
        if (title == null) {
            setTitle(new StringFilter());
        }
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getCategory() {
        return category;
    }

    public Optional<StringFilter> optionalCategory() {
        return Optional.ofNullable(category);
    }

    public StringFilter category() {
        if (category == null) {
            setCategory(new StringFilter());
        }
        return category;
    }

    public void setCategory(StringFilter category) {
        this.category = category;
    }

    public LocalDateFilter getPublishDate() {
        return publishDate;
    }

    public Optional<LocalDateFilter> optionalPublishDate() {
        return Optional.ofNullable(publishDate);
    }

    public LocalDateFilter publishDate() {
        if (publishDate == null) {
            setPublishDate(new LocalDateFilter());
        }
        return publishDate;
    }

    public void setPublishDate(LocalDateFilter publishDate) {
        this.publishDate = publishDate;
    }

    public FloatFilter getPrice() {
        return price;
    }

    public Optional<FloatFilter> optionalPrice() {
        return Optional.ofNullable(price);
    }

    public FloatFilter price() {
        if (price == null) {
            setPrice(new FloatFilter());
        }
        return price;
    }

    public void setPrice(FloatFilter price) {
        this.price = price;
    }

    public StringFilter getIsbn() {
        return isbn;
    }

    public Optional<StringFilter> optionalIsbn() {
        return Optional.ofNullable(isbn);
    }

    public StringFilter isbn() {
        if (isbn == null) {
            setIsbn(new StringFilter());
        }
        return isbn;
    }

    public void setIsbn(StringFilter isbn) {
        this.isbn = isbn;
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
        final BookCriteria that = (BookCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(author, that.author) &&
            Objects.equals(aboutAuthor, that.aboutAuthor) &&
            Objects.equals(title, that.title) &&
            Objects.equals(category, that.category) &&
            Objects.equals(publishDate, that.publishDate) &&
            Objects.equals(price, that.price) &&
            Objects.equals(isbn, that.isbn) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, aboutAuthor, title, category, publishDate, price, isbn, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalAuthor().map(f -> "author=" + f + ", ").orElse("") +
            optionalAboutAuthor().map(f -> "aboutAuthor=" + f + ", ").orElse("") +
            optionalTitle().map(f -> "title=" + f + ", ").orElse("") +
            optionalCategory().map(f -> "category=" + f + ", ").orElse("") +
            optionalPublishDate().map(f -> "publishDate=" + f + ", ").orElse("") +
            optionalPrice().map(f -> "price=" + f + ", ").orElse("") +
            optionalIsbn().map(f -> "isbn=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
