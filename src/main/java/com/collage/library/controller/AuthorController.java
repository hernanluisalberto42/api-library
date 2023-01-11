package com.collage.library.controller;

import com.collage.library.dto.AuthorDto;
import com.collage.library.model.Author;
import com.collage.library.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @GetMapping
    public ResponseEntity<List<AuthorDto>> findAll(){
        return ResponseEntity.ok(
                authorService.findAllAuthors()
                        .stream()
                        .map(AuthorDto::from)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AuthorDto> findAuthorById(@PathVariable Long id){
        return ResponseEntity.ok(
                AuthorDto.from(
                        authorService.findAuthorById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto authorDto){
        return new ResponseEntity<>(
                AuthorDto.from(
                        authorService.saveAuthor(Author.from(authorDto))
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorDto authorDto){
        return ResponseEntity.ok(
                AuthorDto.from(
                        authorService.updateAuthor(id,Author.from(authorDto))
                )
        );
    }

    @DeleteMapping("/del")
    public ResponseEntity<AuthorDto> dellBook(@RequestParam("id") Long id){
        return ResponseEntity.ok(
                AuthorDto.from(
                        authorService.removeAuthor(id)
                )
        );
    }


}
