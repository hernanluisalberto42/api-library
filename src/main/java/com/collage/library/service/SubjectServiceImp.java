package com.collage.library.service;

import com.collage.library.config.CustomNotFoundException;
import com.collage.library.model.Book;
import com.collage.library.model.Subject;
import com.collage.library.repository.BookRepository;
import com.collage.library.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubjectServiceImp implements SubjectService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SubjectRepository subjectRepository;


    @Override
    public List<Subject> findAllSubjects() {
        return StreamSupport.stream(
                subjectRepository.findAll().spliterator(),
                false
        )
                .collect(Collectors.toList());
    }

    @Override
    public Subject findSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(()->new CustomNotFoundException("Not Found that subject!!...."));
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(Long id, Subject subject) {
        return subjectRepository.findById(id)
                .map(obj->{
                    obj.setDate(subject.getDate());
                    obj.setName(subject.getName());
                    return subjectRepository.save(obj);
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found that subject!!...."));
    }

    @Override
    public Subject removeSubject(Long id) {
        return subjectRepository.findById(id)
                .map(obj->{
                    subjectRepository.delete(obj);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found that subject!!...."));
    }

    @Override
    @Transactional
    public Subject addBookToSubject(Long idBook, Long idSubject) {
        return subjectRepository.findById(idSubject)
                .map(obj->{
                    Book book=bookRepository.findById(idBook)
                            .orElseThrow(()->new CustomNotFoundException("Not Found that subject!!...."));
                    obj.addBook(book);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found that subject!!...."));
    }

    @Override
    @Transactional
    public Subject removeBookFromSubject(Long idBook, Long idSubject) {
        return subjectRepository.findById(idSubject)
                .map(obj->{
                    Book book=bookRepository.findById(idBook)
                            .orElseThrow(()->new CustomNotFoundException("Not Found that subject!!...."));
                    obj.removeBook(book);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found that subject!!...."));
    }
}
