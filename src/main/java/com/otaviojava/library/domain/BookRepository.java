package com.otaviojava.library.domain;

import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;

@Repository
public interface BookRepository extends BasicRepository<Book, String> {

}