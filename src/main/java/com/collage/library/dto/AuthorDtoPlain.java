package com.collage.library.dto;
;
import com.collage.library.model.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDtoPlain {

    private Long idAuthor;
    private String firstName;
    private String lastName;

    public static AuthorDtoPlain from(Author author){
        AuthorDtoPlain authorDtoPlain=new AuthorDtoPlain();
        BeanUtils.copyProperties(author,authorDtoPlain);
        return authorDtoPlain;
    }
}
