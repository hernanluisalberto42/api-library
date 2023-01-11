package com.collage.library.service;

import com.collage.library.config.CustomNotFoundException;
import com.collage.library.model.Author;
import com.collage.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImp implements AuthorService{


    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAllAuthors() {
        return  StreamSupport.stream(
                        authorRepository.findAll().spliterator(),
                        false
                ).collect(Collectors.toList());
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(()->new CustomNotFoundException("Not Found Author!!!..."));
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        return authorRepository.findById(id)
                .map(obj->{
                    obj.setFirstName(author.getFirstName());
                    obj.setLastName(author.getLastName());
                    obj.setDate(author.getDate());
                    return authorRepository.save(obj);
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found Author!!!..."));
    }

    @Override
    public Author removeAuthor(Long id) {
        return authorRepository.findById(id)
                .map(obj->{
                    authorRepository.deleteById(id);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found Author!!!..."));
    }
}
