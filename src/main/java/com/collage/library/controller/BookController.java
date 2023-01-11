package com.collage.library.controller;

import com.collage.library.dto.BookDto;
import com.collage.library.model.Book;
import com.collage.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll(){
          return ResponseEntity.ok(
                  bookService.findAllBooks()
                          .stream()
                          .map(BookDto::from)
                          .collect(Collectors.toList())
          );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDto> findBookById(@PathVariable Long id){
        return ResponseEntity.ok(
                BookDto.from(bookService.findBookById(id))
        );
    }

    @PostMapping
    public ResponseEntity<BookDto> saveBook(@Valid @RequestBody BookDto bookDto){
        return new ResponseEntity<>(
                BookDto.from(
                        bookService.saveBook(Book.from(bookDto))
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/edi/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto bookDto){
        return ResponseEntity.ok(
                BookDto.from(bookService.updateBook(id,Book.from(bookDto)))
        );
    }

    @DeleteMapping("/dell")
    public ResponseEntity<BookDto> dellBook(@RequestParam("id") Long id){
        return ResponseEntity.ok(
                BookDto.from(
                        bookService.removeBook(id)
                )
        );
    }

    @PostMapping("/add/{idAuthor}/{idBook}")
    public ResponseEntity<BookDto> addAuthorToBook(@PathVariable Long idAuthor, @PathVariable Long idBook){
        return ResponseEntity.ok(
                BookDto.from(
                        bookService.addAuthorToBook(idAuthor, idBook)
                )
        );
    }

    @DeleteMapping("/remove/{idAuthor}/{idBook}")
    public ResponseEntity<BookDto> dellAuthorFromBook(@PathVariable Long idAuthor, @PathVariable Long idBook){
        return ResponseEntity.ok(
                BookDto.from(
                        bookService.removeAuthorFromBook(idAuthor, idBook)
                )
        );
    }
}
