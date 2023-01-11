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
public class BookDtoLoan {

    private Long idBook;
    private String code;
    private String title;
    private Integer number;

    public static BookDtoLoan from(Book book){
        BookDtoLoan bookDtoLoan=new BookDtoLoan();
        BeanUtils.copyProperties(book,bookDtoLoan);
        return bookDtoLoan;
    }
}
