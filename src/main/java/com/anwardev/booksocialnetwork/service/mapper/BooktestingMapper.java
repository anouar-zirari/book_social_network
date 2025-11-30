package com.anwardev.booksocialnetwork.service.mapper;

import com.anwardev.booksocialnetwork.domain.Booktesting;
import com.anwardev.booksocialnetwork.service.dto.BooktestingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Booktesting} and its DTO {@link BooktestingDTO}.
 */
@Mapper(componentModel = "spring")
public interface BooktestingMapper extends EntityMapper<BooktestingDTO, Booktesting> {}
