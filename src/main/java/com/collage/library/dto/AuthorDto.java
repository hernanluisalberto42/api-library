package com.collage.library.dto;

import com.collage.library.model.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {

    private Long idAuthor;
    private Date date;
    private String firstName;
    private String lastName;
    private List<BookDtoPlain> listBooks;

    public static AuthorDto from(Author author){
        AuthorDto authorDto=new AuthorDto();
        authorDto.setIdAuthor(author.getIdAuthor());
        authorDto.setDate(author.getDate());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        if(Objects.nonNull(author.getListBooks()))
            authorDto.setListBooks(
                    author.getListBooks()
                            .stream()
                            .map(BookDtoPlain::from)
                            .collect(Collectors.toList())
            );
        return authorDto;
    }
}
