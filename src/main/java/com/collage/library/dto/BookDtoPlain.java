package com.collage.library.dto;

import com.collage.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDtoPlain {

    private Long idBook;
    private String code;
    private String title;
    private Integer yearEdition;

    public static BookDtoPlain from(Book book){
        BookDtoPlain bookDtoPlain=new BookDtoPlain();
        BeanUtils.copyProperties(book,bookDtoPlain);
        return bookDtoPlain;
    }
}
