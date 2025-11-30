package com.anwardev.booksocialnetwork.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Review.
 */
@Entity
@Table(name = "review")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "opinion")
    private String opinion;

    @Column(name = "rating")
    private String rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "somthings" }, allowSetters = true)
    private Booktesting manytoone;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Review id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpinion() {
        return this.opinion;
    }

    public Review opinion(String opinion) {
        this.setOpinion(opinion);
        return this;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getRating() {
        return this.rating;
    }

    public Review rating(String rating) {
        this.setRating(rating);
        return this;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Booktesting getManytoone() {
        return this.manytoone;
    }

    public void setManytoone(Booktesting booktesting) {
        this.manytoone = booktesting;
    }

    public Review manytoone(Booktesting booktesting) {
        this.setManytoone(booktesting);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Review user(User user) {
        this.setUser(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Review)) {
            return false;
        }
        return getId() != null && getId().equals(((Review) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Review{" +
            "id=" + getId() +
            ", opinion='" + getOpinion() + "'" +
            ", rating='" + getRating() + "'" +
            "}";
    }
}
