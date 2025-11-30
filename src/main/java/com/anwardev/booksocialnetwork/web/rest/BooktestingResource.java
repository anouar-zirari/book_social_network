package com.anwardev.booksocialnetwork.web.rest;

import com.anwardev.booksocialnetwork.repository.BooktestingRepository;
import com.anwardev.booksocialnetwork.service.BooktestingQueryService;
import com.anwardev.booksocialnetwork.service.BooktestingService;
import com.anwardev.booksocialnetwork.service.criteria.BooktestingCriteria;
import com.anwardev.booksocialnetwork.service.dto.BooktestingDTO;
import com.anwardev.booksocialnetwork.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.anwardev.booksocialnetwork.domain.Booktesting}.
 */
@RestController
@RequestMapping("/api/booktestings")
public class BooktestingResource {

    private static final Logger LOG = LoggerFactory.getLogger(BooktestingResource.class);

    private static final String ENTITY_NAME = "booktesting";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BooktestingService booktestingService;

    private final BooktestingRepository booktestingRepository;

    private final BooktestingQueryService booktestingQueryService;

    public BooktestingResource(
        BooktestingService booktestingService,
        BooktestingRepository booktestingRepository,
        BooktestingQueryService booktestingQueryService
    ) {
        this.booktestingService = booktestingService;
        this.booktestingRepository = booktestingRepository;
        this.booktestingQueryService = booktestingQueryService;
    }

    /**
     * {@code POST  /booktestings} : Create a new booktesting.
     *
     * @param booktestingDTO the booktestingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new booktestingDTO, or with status {@code 400 (Bad Request)} if the booktesting has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<BooktestingDTO> createBooktesting(@Valid @RequestBody BooktestingDTO booktestingDTO) throws URISyntaxException {
        LOG.debug("REST request to save Booktesting : {}", booktestingDTO);
        if (booktestingDTO.getId() != null) {
            throw new BadRequestAlertException("A new booktesting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        booktestingDTO = booktestingService.save(booktestingDTO);
        return ResponseEntity.created(new URI("/api/booktestings/" + booktestingDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, booktestingDTO.getId().toString()))
            .body(booktestingDTO);
    }

    /**
     * {@code PUT  /booktestings/:id} : Updates an existing booktesting.
     *
     * @param id the id of the booktestingDTO to save.
     * @param booktestingDTO the booktestingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated booktestingDTO,
     * or with status {@code 400 (Bad Request)} if the booktestingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the booktestingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BooktestingDTO> updateBooktesting(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BooktestingDTO booktestingDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Booktesting : {}, {}", id, booktestingDTO);
        if (booktestingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, booktestingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!booktestingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        booktestingDTO = booktestingService.update(booktestingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, booktestingDTO.getId().toString()))
            .body(booktestingDTO);
    }

    /**
     * {@code PATCH  /booktestings/:id} : Partial updates given fields of an existing booktesting, field will ignore if it is null
     *
     * @param id the id of the booktestingDTO to save.
     * @param booktestingDTO the booktestingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated booktestingDTO,
     * or with status {@code 400 (Bad Request)} if the booktestingDTO is not valid,
     * or with status {@code 404 (Not Found)} if the booktestingDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the booktestingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BooktestingDTO> partialUpdateBooktesting(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BooktestingDTO booktestingDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Booktesting partially : {}, {}", id, booktestingDTO);
        if (booktestingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, booktestingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!booktestingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BooktestingDTO> result = booktestingService.partialUpdate(booktestingDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, booktestingDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /booktestings} : get all the booktestings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of booktestings in body.
     */
    @GetMapping("")
    public ResponseEntity<List<BooktestingDTO>> getAllBooktestings(
        BooktestingCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get Booktestings by criteria: {}", criteria);

        Page<BooktestingDTO> page = booktestingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /booktestings/count} : count all the booktestings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countBooktestings(BooktestingCriteria criteria) {
        LOG.debug("REST request to count Booktestings by criteria: {}", criteria);
        return ResponseEntity.ok().body(booktestingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /booktestings/:id} : get the "id" booktesting.
     *
     * @param id the id of the booktestingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the booktestingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BooktestingDTO> getBooktesting(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Booktesting : {}", id);
        Optional<BooktestingDTO> booktestingDTO = booktestingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(booktestingDTO);
    }

    /**
     * {@code DELETE  /booktestings/:id} : delete the "id" booktesting.
     *
     * @param id the id of the booktestingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooktesting(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Booktesting : {}", id);
        booktestingService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
