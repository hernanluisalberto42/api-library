package com.collage.library.controller;

import com.collage.library.dto.BookDto;
import com.collage.library.dto.SubjectDto;
import com.collage.library.model.Book;
import com.collage.library.model.Subject;
import com.collage.library.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectDto>> findAll(){
        return ResponseEntity.ok(
                subjectService.findAllSubjects()
                        .stream()
                        .map(SubjectDto::from)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SubjectDto> findSubjectById(@PathVariable Long id){
        return ResponseEntity.ok(
                SubjectDto.from(subjectService.findSubjectById(id))
        );
    }

    @PostMapping
    public ResponseEntity<SubjectDto> saveSubject(@Valid @RequestBody SubjectDto subjectDto){
        return new ResponseEntity<>(
                SubjectDto.from(
                        subjectService.saveSubject(Subject.from(subjectDto))
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/edi/{id}")
    public ResponseEntity<SubjectDto> updateBook(@PathVariable Long id, @Valid @RequestBody SubjectDto subjectDto){
        return ResponseEntity.ok(
                SubjectDto.from(subjectService.updateSubject(id,Subject.from(subjectDto)))
        );
    }

    @DeleteMapping("/dell")
    public ResponseEntity<SubjectDto> dellSubject(@RequestParam("id") Long id){
        return ResponseEntity.ok(
                SubjectDto.from(
                        subjectService.removeSubject(id)
                )
        );
    }

    @PostMapping("/add/{idBook}/{idSubject}")
    public ResponseEntity<SubjectDto> addBookToSubject(@PathVariable Long idBook, @PathVariable Long idSubject){
        return ResponseEntity.ok(
                SubjectDto.from(
                        subjectService.addBookToSubject(idBook, idSubject)
                )
        );
    }

    @DeleteMapping("/remove/{idBook}/{idSubject}")
    public ResponseEntity<SubjectDto> dellBookFromSubject(@PathVariable Long idBook, @PathVariable Long idSubject){
        return ResponseEntity.ok(
                SubjectDto.from(
                        subjectService.removeBookFromSubject(idBook, idSubject)
                )
        );
    }
}
