package com.anwardev.booksocialnetwork.repository;

import com.anwardev.booksocialnetwork.domain.Review;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Review entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {
    @Query("select review from Review review where review.user.login = ?#{authentication.name}")
    List<Review> findByUserIsCurrentUser();
}
