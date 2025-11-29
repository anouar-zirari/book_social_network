package com.anwardev.booksocialnetwork.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.anwardev.booksocialnetwork.domain.Book} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BookDTO implements Serializable {

    private Long id;

    private String author;

    private String aboutAuthor;

    private String title;

    private String category;

    private LocalDate publishDate;

    private Float price;

    private String isbn;

    @Lob
    private byte[] authorImage;

    private String authorImageContentType;

    @Lob
    private byte[] coverImage;

    private String coverImageContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAboutAuthor() {
        return aboutAuthor;
    }

    public void setAboutAuthor(String aboutAuthor) {
        this.aboutAuthor = aboutAuthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public byte[] getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(byte[] authorImage) {
        this.authorImage = authorImage;
    }

    public String getAuthorImageContentType() {
        return authorImageContentType;
    }

    public void setAuthorImageContentType(String authorImageContentType) {
        this.authorImageContentType = authorImageContentType;
    }

    public byte[] getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }

    public String getCoverImageContentType() {
        return coverImageContentType;
    }

    public void setCoverImageContentType(String coverImageContentType) {
        this.coverImageContentType = coverImageContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookDTO)) {
            return false;
        }

        BookDTO bookDTO = (BookDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bookDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookDTO{" +
            "id=" + getId() +
            ", author='" + getAuthor() + "'" +
            ", aboutAuthor='" + getAboutAuthor() + "'" +
            ", title='" + getTitle() + "'" +
            ", category='" + getCategory() + "'" +
            ", publishDate='" + getPublishDate() + "'" +
            ", price=" + getPrice() +
            ", isbn='" + getIsbn() + "'" +
            ", authorImage='" + getAuthorImage() + "'" +
            ", coverImage='" + getCoverImage() + "'" +
            "}";
    }
}
