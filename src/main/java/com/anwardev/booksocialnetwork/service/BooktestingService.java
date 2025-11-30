package com.anwardev.booksocialnetwork.service;

import com.anwardev.booksocialnetwork.service.dto.BooktestingDTO;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.anwardev.booksocialnetwork.domain.Booktesting}.
 */
public interface BooktestingService {
    /**
     * Save a booktesting.
     *
     * @param booktestingDTO the entity to save.
     * @return the persisted entity.
     */
    BooktestingDTO save(BooktestingDTO booktestingDTO);

    /**
     * Updates a booktesting.
     *
     * @param booktestingDTO the entity to update.
     * @return the persisted entity.
     */
    BooktestingDTO update(BooktestingDTO booktestingDTO);

    /**
     * Partially updates a booktesting.
     *
     * @param booktestingDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BooktestingDTO> partialUpdate(BooktestingDTO booktestingDTO);

    /**
     * Get the "id" booktesting.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BooktestingDTO> findOne(Long id);

    /**
     * Delete the "id" booktesting.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
