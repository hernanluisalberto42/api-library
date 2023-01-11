package com.collage.library.dto;

import com.collage.library.model.Author;
import com.collage.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    private Long idBook;
    private String codeDto;
    private String titleDto;
    private List<AuthorDtoPlain> listAuthors;
    private Integer yearEditionDto;
    private String editorialDto;
    private String countryFromBookDto;
    private String resumeDto;
    private Integer countPagesDto;
    private Integer numberDto;


    public static BookDto from(Book book){
        BookDto bookDto=new BookDto();
        bookDto.setIdBook(book.getIdBook());
        bookDto.setCodeDto(book.getCode());
        bookDto.setTitleDto(book.getTitle());
        bookDto.setYearEditionDto(book.getYearEdition());
        bookDto.setEditorialDto(book.getEditorial());
        bookDto.setCountryFromBookDto(book.getCountryFromBook());
        bookDto.setResumeDto(book.getResume());
        bookDto.setCountPagesDto(book.getCountPages());
        bookDto.setNumberDto(book.getNumber());
        if(Objects.nonNull(book.getListAuthors()))
            bookDto.setListAuthors(
                    book.getListAuthors()
                            .stream()
                            .map(AuthorDtoPlain::from)
                            .collect(Collectors.toList())
            );
        return bookDto;
    }
}
