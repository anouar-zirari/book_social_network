package com.anwardev.booksocialnetwork.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Booktesting.
 */
@Entity
@Table(name = "booktesting")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Booktesting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manytoone")
    @JsonIgnoreProperties(value = { "manytoone", "user" }, allowSetters = true)
    private Set<Review> somthings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Booktesting id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Booktesting name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Booktesting lastname(String lastname) {
        this.setLastname(lastname);
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public Booktesting email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Review> getSomthings() {
        return this.somthings;
    }

    public void setSomthings(Set<Review> reviews) {
        if (this.somthings != null) {
            this.somthings.forEach(i -> i.setManytoone(null));
        }
        if (reviews != null) {
            reviews.forEach(i -> i.setManytoone(this));
        }
        this.somthings = reviews;
    }

    public Booktesting somthings(Set<Review> reviews) {
        this.setSomthings(reviews);
        return this;
    }

    public Booktesting addSomthing(Review review) {
        this.somthings.add(review);
        review.setManytoone(this);
        return this;
    }

    public Booktesting removeSomthing(Review review) {
        this.somthings.remove(review);
        review.setManytoone(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Booktesting)) {
            return false;
        }
        return getId() != null && getId().equals(((Booktesting) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Booktesting{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
