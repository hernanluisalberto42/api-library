package com.collage.library.service;

import com.collage.library.model.Subject;

import java.util.List;

public interface SubjectService {

    public List<Subject> findAllSubjects();

    public Subject findSubjectById(Long id);

    public Subject saveSubject(Subject subject);

    public Subject updateSubject(Long id, Subject subject);

    public Subject removeSubject(Long id);

    public Subject addBookToSubject(Long idBook,Long idSubject);

    public Subject removeBookFromSubject(Long idBook,Long idSubject);
}
