package com.anwardev.booksocialnetwork.repository;

import com.anwardev.booksocialnetwork.domain.Booktesting;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Booktesting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BooktestingRepository extends JpaRepository<Booktesting, Long>, JpaSpecificationExecutor<Booktesting> {}
