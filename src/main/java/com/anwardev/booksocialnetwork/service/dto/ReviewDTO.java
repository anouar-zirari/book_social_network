package com.anwardev.booksocialnetwork.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.anwardev.booksocialnetwork.domain.Review} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReviewDTO implements Serializable {

    private Long id;

    private String opinion;

    private String rating;

    private BooktestingDTO manytoone;

    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public BooktestingDTO getManytoone() {
        return manytoone;
    }

    public void setManytoone(BooktestingDTO manytoone) {
        this.manytoone = manytoone;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReviewDTO)) {
            return false;
        }

        ReviewDTO reviewDTO = (ReviewDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, reviewDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReviewDTO{" +
            "id=" + getId() +
            ", opinion='" + getOpinion() + "'" +
            ", rating='" + getRating() + "'" +
            ", manytoone=" + getManytoone() +
            ", user=" + getUser() +
            "}";
    }
}
