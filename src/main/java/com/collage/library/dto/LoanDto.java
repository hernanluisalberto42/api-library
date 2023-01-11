package com.collage.library.dto;

import com.collage.library.model.Loan;
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
public class LoanDto {

    private Long idLoan;
    private Date date;
    private Date dateReturn;
    private List<BookDtoLoan> listBook;
    private UserDto user;

    public static LoanDto from(Loan loan){
        LoanDto loanDto=new LoanDto();
        loanDto.setIdLoan(loan.getIdLoan());
        loanDto.setDate(loan.getDate());
        loanDto.setDateReturn(loan.getDateReturn());
        if(Objects.nonNull(loan.getListBook()))
            loanDto.setListBook(
                    loan.getListBook()
                            .stream()
                            .map(BookDtoLoan::from)
                            .collect(Collectors.toList())
            );
        if(Objects.nonNull(loan.getUser()))
            loanDto.setUser(UserDto.from(loan.getUser()));
        return loanDto;
    }

}
