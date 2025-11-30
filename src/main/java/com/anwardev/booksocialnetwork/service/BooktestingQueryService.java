package com.anwardev.booksocialnetwork.service;

import com.anwardev.booksocialnetwork.domain.*; // for static metamodels
import com.anwardev.booksocialnetwork.domain.Booktesting;
import com.anwardev.booksocialnetwork.repository.BooktestingRepository;
import com.anwardev.booksocialnetwork.service.criteria.BooktestingCriteria;
import com.anwardev.booksocialnetwork.service.dto.BooktestingDTO;
import com.anwardev.booksocialnetwork.service.mapper.BooktestingMapper;
import jakarta.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Booktesting} entities in the database.
 * The main input is a {@link BooktestingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link BooktestingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BooktestingQueryService extends QueryService<Booktesting> {

    private static final Logger LOG = LoggerFactory.getLogger(BooktestingQueryService.class);

    private final BooktestingRepository booktestingRepository;

    private final BooktestingMapper booktestingMapper;

    public BooktestingQueryService(BooktestingRepository booktestingRepository, BooktestingMapper booktestingMapper) {
        this.booktestingRepository = booktestingRepository;
        this.booktestingMapper = booktestingMapper;
    }

    /**
     * Return a {@link Page} of {@link BooktestingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BooktestingDTO> findByCriteria(BooktestingCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Booktesting> specification = createSpecification(criteria);
        return booktestingRepository.findAll(specification, page).map(booktestingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BooktestingCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Booktesting> specification = createSpecification(criteria);
        return booktestingRepository.count(specification);
    }

    /**
     * Function to convert {@link BooktestingCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Booktesting> createSpecification(BooktestingCriteria criteria) {
        Specification<Booktesting> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildRangeSpecification(criteria.getId(), Booktesting_.id),
                buildStringSpecification(criteria.getName(), Booktesting_.name),
                buildStringSpecification(criteria.getLastname(), Booktesting_.lastname),
                buildStringSpecification(criteria.getEmail(), Booktesting_.email),
                buildSpecification(criteria.getSomthingId(), root -> root.join(Booktesting_.somthings, JoinType.LEFT).get(Review_.id))
            );
        }
        return specification;
    }
}
