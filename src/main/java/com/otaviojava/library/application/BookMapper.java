package com.otaviojava.library.application;

import com.otaviojava.library.api.BookRequest;
import com.otaviojava.library.api.BookResponse;
import com.otaviojava.library.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface BookMapper {

    BookResponse toResponse(Book book);

    Book toEntity(BookRequest bookRequest);

    @Mapping(target = "id", source = "id")
    Book toEntity(BookRequest bookRequest, String id);

}