package com.collage.library.model;

import com.collage.library.dto.LoanDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "loan_tb")
public class Loan implements Serializable {

    private static final Long serialVersionUID=1L;

    @Id
    @SequenceGenerator(
            name = "loan_sequence",
            sequenceName = "loan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loan_sequence"
    )

    @Column(name = "id_loan")
    private Long idLoan;

    private Date date;

    @NotNull
    @Column(name = "date_return")
    private Date dateReturn;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "idLoan",
            referencedColumnName = "id_loan"
    )
    private List<Book> listBook;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "idUser",
            referencedColumnName = "id_user"
    )
    private User user;

    public void addBook(Book book){
        this.listBook.add(book);
    }

    public void removeBook(Book book){
        this.listBook.remove(book);
    }

    public static Loan from(LoanDto loanDto){
        Loan loan=new Loan();
        loan.setDate(loanDto.getDate());
        loan.setDateReturn(loanDto.getDateReturn());
        return loan;
    }
}
