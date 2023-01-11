package com.collage.library.service;

import com.collage.library.config.CustomNotFoundException;
import com.collage.library.config.RestPreconditions;
import com.collage.library.model.Author;
import com.collage.library.model.Book;
import com.collage.library.repository.AuthorRepository;
import com.collage.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImp implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public List<Book> findAllBooks() {
        return StreamSupport.stream(
                bookRepository.findAll().spliterator(),
                false
        )
                .collect(Collectors.toList());
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()->new CustomNotFoundException("Not Found Book!!!..."));
    }

    @Override
    public Book saveBook(Book book) {
        RestPreconditions.checkIfYearIsGreaterThan1500(book.getYearEdition());
        RestPreconditions.checkIfNumberIsGreaterThanOne(book.getNumber());
        RestPreconditions.checkIfCountPagesGraterThanNine(book.getCountPages());
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        return bookRepository.findById(id)
                .map(obj->{
                    RestPreconditions.checkIfYearIsGreaterThan1500(book.getYearEdition());
                    RestPreconditions.checkIfNumberIsGreaterThanOne(book.getNumber());
                    RestPreconditions.checkIfCountPagesGraterThanNine(book.getCountPages());
                    obj.setCode(book.getCode());
                    obj.setTitle(book.getTitle());
                    obj.setCountryFromBook(book.getCountryFromBook());
                    obj.setYearEdition(book.getYearEdition());
                    obj.setEditorial(book.getEditorial());
                    obj.setCountPages(book.getCountPages());
                    obj.setResume(book.getResume());
                    obj.setNumber(book.getNumber());
                    return bookRepository.save(obj);
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found Book!!!..."));
    }

    @Override
    public Book removeBook(Long id) {
        return bookRepository.findById(id)
                .map(obj->{
                    bookRepository.deleteById(id);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found Book!!!..."));
    }

    @Override
    @Transactional
    public Book addAuthorToBook(Long idAuthor, Long idBook) {
        return bookRepository.findById(idBook)
                .map(obj->{
                    Author author=authorRepository.findById(idAuthor)
                            .orElseThrow(()->new CustomNotFoundException("Not Found Author!!..."));
                    obj.addAuthor(author);
                    author.addBook(obj);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found Book!!!..."));
    }

    @Override
    @Transactional
    public Book removeAuthorFromBook(Long idAuthor, Long idBook) {
        return bookRepository.findById(idBook)
                .map(obj->{
                    Author author=authorRepository.findById(idAuthor)
                            .orElseThrow(()->new RuntimeException("Not Found!!"));
                    obj.removeAuthor(author);
                    author.removeBook(obj);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found Book!!!..."));
    }
}
