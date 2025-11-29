package com.anwardev.booksocialnetwork.service.mapper;

import com.anwardev.booksocialnetwork.domain.Book;
import com.anwardev.booksocialnetwork.service.dto.BookDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Book} and its DTO {@link BookDTO}.
 */
@Mapper(componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDTO, Book> {}
