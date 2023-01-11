package com.collage.library.service;

import com.collage.library.model.Author;

import java.util.List;

public interface AuthorService {

    public List<Author> findAllAuthors();

    public Author findAuthorById(Long id);

    public Author saveAuthor(Author author);

    public Author updateAuthor(Long id, Author author);

    public Author removeAuthor(Long id);

}
