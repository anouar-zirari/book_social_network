package com.anwardev.booksocialnetwork.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Book.
 */
@Entity
@Table(name = "book")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "about_author")
    private String aboutAuthor;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "price")
    private Float price;

    @Column(name = "isbn")
    private String isbn;

    @Lob
    @Column(name = "author_image")
    private byte[] authorImage;

    @Column(name = "author_image_content_type")
    private String authorImageContentType;

    @Lob
    @Column(name = "cover_image")
    private byte[] coverImage;

    @Column(name = "cover_image_content_type")
    private String coverImageContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Book id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return this.author;
    }

    public Book author(String author) {
        this.setAuthor(author);
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAboutAuthor() {
        return this.aboutAuthor;
    }

    public Book aboutAuthor(String aboutAuthor) {
        this.setAboutAuthor(aboutAuthor);
        return this;
    }

    public void setAboutAuthor(String aboutAuthor) {
        this.aboutAuthor = aboutAuthor;
    }

    public String getTitle() {
        return this.title;
    }

    public Book title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return this.category;
    }

    public Book category(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getPublishDate() {
        return this.publishDate;
    }

    public Book publishDate(LocalDate publishDate) {
        this.setPublishDate(publishDate);
        return this;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Float getPrice() {
        return this.price;
    }

    public Book price(Float price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public Book isbn(String isbn) {
        this.setIsbn(isbn);
        return this;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public byte[] getAuthorImage() {
        return this.authorImage;
    }

    public Book authorImage(byte[] authorImage) {
        this.setAuthorImage(authorImage);
        return this;
    }

    public void setAuthorImage(byte[] authorImage) {
        this.authorImage = authorImage;
    }

    public String getAuthorImageContentType() {
        return this.authorImageContentType;
    }

    public Book authorImageContentType(String authorImageContentType) {
        this.authorImageContentType = authorImageContentType;
        return this;
    }

    public void setAuthorImageContentType(String authorImageContentType) {
        this.authorImageContentType = authorImageContentType;
    }

    public byte[] getCoverImage() {
        return this.coverImage;
    }

    public Book coverImage(byte[] coverImage) {
        this.setCoverImage(coverImage);
        return this;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }

    public String getCoverImageContentType() {
        return this.coverImageContentType;
    }

    public Book coverImageContentType(String coverImageContentType) {
        this.coverImageContentType = coverImageContentType;
        return this;
    }

    public void setCoverImageContentType(String coverImageContentType) {
        this.coverImageContentType = coverImageContentType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        return getId() != null && getId().equals(((Book) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Book{" +
            "id=" + getId() +
            ", author='" + getAuthor() + "'" +
            ", aboutAuthor='" + getAboutAuthor() + "'" +
            ", title='" + getTitle() + "'" +
            ", category='" + getCategory() + "'" +
            ", publishDate='" + getPublishDate() + "'" +
            ", price=" + getPrice() +
            ", isbn='" + getIsbn() + "'" +
            ", authorImage='" + getAuthorImage() + "'" +
            ", authorImageContentType='" + getAuthorImageContentType() + "'" +
            ", coverImage='" + getCoverImage() + "'" +
            ", coverImageContentType='" + getCoverImageContentType() + "'" +
            "}";
    }
}
