package com.anwardev.booksocialnetwork.service.impl;

import com.anwardev.booksocialnetwork.domain.Booktesting;
import com.anwardev.booksocialnetwork.repository.BooktestingRepository;
import com.anwardev.booksocialnetwork.service.BooktestingService;
import com.anwardev.booksocialnetwork.service.dto.BooktestingDTO;
import com.anwardev.booksocialnetwork.service.mapper.BooktestingMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.anwardev.booksocialnetwork.domain.Booktesting}.
 */
@Service
@Transactional
public class BooktestingServiceImpl implements BooktestingService {

    private static final Logger LOG = LoggerFactory.getLogger(BooktestingServiceImpl.class);

    private final BooktestingRepository booktestingRepository;

    private final BooktestingMapper booktestingMapper;

    public BooktestingServiceImpl(BooktestingRepository booktestingRepository, BooktestingMapper booktestingMapper) {
        this.booktestingRepository = booktestingRepository;
        this.booktestingMapper = booktestingMapper;
    }

    @Override
    public BooktestingDTO save(BooktestingDTO booktestingDTO) {
        LOG.debug("Request to save Booktesting : {}", booktestingDTO);
        Booktesting booktesting = booktestingMapper.toEntity(booktestingDTO);
        booktesting = booktestingRepository.save(booktesting);
        return booktestingMapper.toDto(booktesting);
    }

    @Override
    public BooktestingDTO update(BooktestingDTO booktestingDTO) {
        LOG.debug("Request to update Booktesting : {}", booktestingDTO);
        Booktesting booktesting = booktestingMapper.toEntity(booktestingDTO);
        booktesting = booktestingRepository.save(booktesting);
        return booktestingMapper.toDto(booktesting);
    }

    @Override
    public Optional<BooktestingDTO> partialUpdate(BooktestingDTO booktestingDTO) {
        LOG.debug("Request to partially update Booktesting : {}", booktestingDTO);

        return booktestingRepository
            .findById(booktestingDTO.getId())
            .map(existingBooktesting -> {
                booktestingMapper.partialUpdate(existingBooktesting, booktestingDTO);

                return existingBooktesting;
            })
            .map(booktestingRepository::save)
            .map(booktestingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BooktestingDTO> findOne(Long id) {
        LOG.debug("Request to get Booktesting : {}", id);
        return booktestingRepository.findById(id).map(booktestingMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Booktesting : {}", id);
        booktestingRepository.deleteById(id);
    }
}
