package com.anwardev.booksocialnetwork.web.rest;

import static com.anwardev.booksocialnetwork.domain.BooktestingAsserts.*;
import static com.anwardev.booksocialnetwork.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.anwardev.booksocialnetwork.IntegrationTest;
import com.anwardev.booksocialnetwork.domain.Booktesting;
import com.anwardev.booksocialnetwork.repository.BooktestingRepository;
import com.anwardev.booksocialnetwork.service.dto.BooktestingDTO;
import com.anwardev.booksocialnetwork.service.mapper.BooktestingMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BooktestingResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BooktestingResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/booktestings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BooktestingRepository booktestingRepository;

    @Autowired
    private BooktestingMapper booktestingMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBooktestingMockMvc;

    private Booktesting booktesting;

    private Booktesting insertedBooktesting;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Booktesting createEntity() {
        return new Booktesting().name(DEFAULT_NAME).lastname(DEFAULT_LASTNAME).email(DEFAULT_EMAIL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Booktesting createUpdatedEntity() {
        return new Booktesting().name(UPDATED_NAME).lastname(UPDATED_LASTNAME).email(UPDATED_EMAIL);
    }

    @BeforeEach
    void initTest() {
        booktesting = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedBooktesting != null) {
            booktestingRepository.delete(insertedBooktesting);
            insertedBooktesting = null;
        }
    }

    @Test
    @Transactional
    void createBooktesting() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Booktesting
        BooktestingDTO booktestingDTO = booktestingMapper.toDto(booktesting);
        var returnedBooktestingDTO = om.readValue(
            restBooktestingMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(booktestingDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            BooktestingDTO.class
        );

        // Validate the Booktesting in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedBooktesting = booktestingMapper.toEntity(returnedBooktestingDTO);
        assertBooktestingUpdatableFieldsEquals(returnedBooktesting, getPersistedBooktesting(returnedBooktesting));

        insertedBooktesting = returnedBooktesting;
    }

    @Test
    @Transactional
    void createBooktestingWithExistingId() throws Exception {
        // Create the Booktesting with an existing ID
        booktesting.setId(1L);
        BooktestingDTO booktestingDTO = booktestingMapper.toDto(booktesting);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBooktestingMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(booktestingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Booktesting in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        booktesting.setEmail(null);

        // Create the Booktesting, which fails.
        BooktestingDTO booktestingDTO = booktestingMapper.toDto(booktesting);

        restBooktestingMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(booktestingDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBooktestings() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList
        restBooktestingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(booktesting.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }

    @Test
    @Transactional
    void getBooktesting() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get the booktesting
        restBooktestingMockMvc
            .perform(get(ENTITY_API_URL_ID, booktesting.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(booktesting.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }

    @Test
    @Transactional
    void getBooktestingsByIdFiltering() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        Long id = booktesting.getId();

        defaultBooktestingFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultBooktestingFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultBooktestingFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllBooktestingsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where name equals to
        defaultBooktestingFiltering("name.equals=" + DEFAULT_NAME, "name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllBooktestingsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where name in
        defaultBooktestingFiltering("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME, "name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllBooktestingsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where name is not null
        defaultBooktestingFiltering("name.specified=true", "name.specified=false");
    }

    @Test
    @Transactional
    void getAllBooktestingsByNameContainsSomething() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where name contains
        defaultBooktestingFiltering("name.contains=" + DEFAULT_NAME, "name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllBooktestingsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where name does not contain
        defaultBooktestingFiltering("name.doesNotContain=" + UPDATED_NAME, "name.doesNotContain=" + DEFAULT_NAME);
    }

    @Test
    @Transactional
    void getAllBooktestingsByLastnameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where lastname equals to
        defaultBooktestingFiltering("lastname.equals=" + DEFAULT_LASTNAME, "lastname.equals=" + UPDATED_LASTNAME);
    }

    @Test
    @Transactional
    void getAllBooktestingsByLastnameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where lastname in
        defaultBooktestingFiltering("lastname.in=" + DEFAULT_LASTNAME + "," + UPDATED_LASTNAME, "lastname.in=" + UPDATED_LASTNAME);
    }

    @Test
    @Transactional
    void getAllBooktestingsByLastnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where lastname is not null
        defaultBooktestingFiltering("lastname.specified=true", "lastname.specified=false");
    }

    @Test
    @Transactional
    void getAllBooktestingsByLastnameContainsSomething() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where lastname contains
        defaultBooktestingFiltering("lastname.contains=" + DEFAULT_LASTNAME, "lastname.contains=" + UPDATED_LASTNAME);
    }

    @Test
    @Transactional
    void getAllBooktestingsByLastnameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where lastname does not contain
        defaultBooktestingFiltering("lastname.doesNotContain=" + UPDATED_LASTNAME, "lastname.doesNotContain=" + DEFAULT_LASTNAME);
    }

    @Test
    @Transactional
    void getAllBooktestingsByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where email equals to
        defaultBooktestingFiltering("email.equals=" + DEFAULT_EMAIL, "email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllBooktestingsByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where email in
        defaultBooktestingFiltering("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL, "email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllBooktestingsByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where email is not null
        defaultBooktestingFiltering("email.specified=true", "email.specified=false");
    }

    @Test
    @Transactional
    void getAllBooktestingsByEmailContainsSomething() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where email contains
        defaultBooktestingFiltering("email.contains=" + DEFAULT_EMAIL, "email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void getAllBooktestingsByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        // Get all the booktestingList where email does not contain
        defaultBooktestingFiltering("email.doesNotContain=" + UPDATED_EMAIL, "email.doesNotContain=" + DEFAULT_EMAIL);
    }

    private void defaultBooktestingFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultBooktestingShouldBeFound(shouldBeFound);
        defaultBooktestingShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBooktestingShouldBeFound(String filter) throws Exception {
        restBooktestingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(booktesting.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));

        // Check, that the count call also returns 1
        restBooktestingMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBooktestingShouldNotBeFound(String filter) throws Exception {
        restBooktestingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBooktestingMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingBooktesting() throws Exception {
        // Get the booktesting
        restBooktestingMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBooktesting() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the booktesting
        Booktesting updatedBooktesting = booktestingRepository.findById(booktesting.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedBooktesting are not directly saved in db
        em.detach(updatedBooktesting);
        updatedBooktesting.name(UPDATED_NAME).lastname(UPDATED_LASTNAME).email(UPDATED_EMAIL);
        BooktestingDTO booktestingDTO = booktestingMapper.toDto(updatedBooktesting);

        restBooktestingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, booktestingDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(booktestingDTO))
            )
            .andExpect(status().isOk());

        // Validate the Booktesting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBooktestingToMatchAllProperties(updatedBooktesting);
    }

    @Test
    @Transactional
    void putNonExistingBooktesting() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        booktesting.setId(longCount.incrementAndGet());

        // Create the Booktesting
        BooktestingDTO booktestingDTO = booktestingMapper.toDto(booktesting);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBooktestingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, booktestingDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(booktestingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Booktesting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBooktesting() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        booktesting.setId(longCount.incrementAndGet());

        // Create the Booktesting
        BooktestingDTO booktestingDTO = booktestingMapper.toDto(booktesting);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBooktestingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(booktestingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Booktesting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBooktesting() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        booktesting.setId(longCount.incrementAndGet());

        // Create the Booktesting
        BooktestingDTO booktestingDTO = booktestingMapper.toDto(booktesting);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBooktestingMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(booktestingDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Booktesting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBooktestingWithPatch() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the booktesting using partial update
        Booktesting partialUpdatedBooktesting = new Booktesting();
        partialUpdatedBooktesting.setId(booktesting.getId());

        partialUpdatedBooktesting.name(UPDATED_NAME).email(UPDATED_EMAIL);

        restBooktestingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBooktesting.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBooktesting))
            )
            .andExpect(status().isOk());

        // Validate the Booktesting in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBooktestingUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedBooktesting, booktesting),
            getPersistedBooktesting(booktesting)
        );
    }

    @Test
    @Transactional
    void fullUpdateBooktestingWithPatch() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the booktesting using partial update
        Booktesting partialUpdatedBooktesting = new Booktesting();
        partialUpdatedBooktesting.setId(booktesting.getId());

        partialUpdatedBooktesting.name(UPDATED_NAME).lastname(UPDATED_LASTNAME).email(UPDATED_EMAIL);

        restBooktestingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBooktesting.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBooktesting))
            )
            .andExpect(status().isOk());

        // Validate the Booktesting in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBooktestingUpdatableFieldsEquals(partialUpdatedBooktesting, getPersistedBooktesting(partialUpdatedBooktesting));
    }

    @Test
    @Transactional
    void patchNonExistingBooktesting() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        booktesting.setId(longCount.incrementAndGet());

        // Create the Booktesting
        BooktestingDTO booktestingDTO = booktestingMapper.toDto(booktesting);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBooktestingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, booktestingDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(booktestingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Booktesting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBooktesting() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        booktesting.setId(longCount.incrementAndGet());

        // Create the Booktesting
        BooktestingDTO booktestingDTO = booktestingMapper.toDto(booktesting);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBooktestingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(booktestingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Booktesting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBooktesting() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        booktesting.setId(longCount.incrementAndGet());

        // Create the Booktesting
        BooktestingDTO booktestingDTO = booktestingMapper.toDto(booktesting);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBooktestingMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(booktestingDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Booktesting in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBooktesting() throws Exception {
        // Initialize the database
        insertedBooktesting = booktestingRepository.saveAndFlush(booktesting);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the booktesting
        restBooktestingMockMvc
            .perform(delete(ENTITY_API_URL_ID, booktesting.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return booktestingRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Booktesting getPersistedBooktesting(Booktesting booktesting) {
        return booktestingRepository.findById(booktesting.getId()).orElseThrow();
    }

    protected void assertPersistedBooktestingToMatchAllProperties(Booktesting expectedBooktesting) {
        assertBooktestingAllPropertiesEquals(expectedBooktesting, getPersistedBooktesting(expectedBooktesting));
    }

    protected void assertPersistedBooktestingToMatchUpdatableProperties(Booktesting expectedBooktesting) {
        assertBooktestingAllUpdatablePropertiesEquals(expectedBooktesting, getPersistedBooktesting(expectedBooktesting));
    }
}
