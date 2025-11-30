package com.anwardev.booksocialnetwork.service.mapper;

import com.anwardev.booksocialnetwork.domain.Booktesting;
import com.anwardev.booksocialnetwork.domain.Review;
import com.anwardev.booksocialnetwork.domain.User;
import com.anwardev.booksocialnetwork.service.dto.BooktestingDTO;
import com.anwardev.booksocialnetwork.service.dto.ReviewDTO;
import com.anwardev.booksocialnetwork.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Review} and its DTO {@link ReviewDTO}.
 */
@Mapper(componentModel = "spring")
public interface ReviewMapper extends EntityMapper<ReviewDTO, Review> {
    @Mapping(target = "manytoone", source = "manytoone", qualifiedByName = "booktestingId")
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    ReviewDTO toDto(Review s);

    @Named("booktestingId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BooktestingDTO toDtoBooktestingId(Booktesting booktesting);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
