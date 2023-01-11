package com.collage.library.service;

import com.collage.library.model.Book;

import java.util.List;

public interface BookService {

    public List<Book> findAllBooks();

    public Book findBookById(Long id);

    public Book saveBook(Book book);

    public Book updateBook(Long id, Book book);

    public Book removeBook(Long id);

    public Book addAuthorToBook(Long idAuthor,Long idBook);

    public Book removeAuthorFromBook(Long idAuthor,Long idBook);
}
