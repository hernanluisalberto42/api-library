package com.collage.library.service;

import com.collage.library.model.Loan;

import java.util.List;

public interface LoanService {

    public List<Loan> findAllLoans();

    public Loan findLoanById(Long id);

    public Loan saveLoan(Loan loan);

    public Loan updateLoan(Long id, Loan loan);

    public Loan removeLoan(Long id);

    public Loan addBookToLoan(Long idBook,Long idLoan);

    public Loan removeBookFromLoan(Long idBook,Long idLoan);

    public Loan addUserToLoan(Long idUser,Long idLoan);

    public Loan removeUserFromLoan(Long idUser,Long idLoan);

}
