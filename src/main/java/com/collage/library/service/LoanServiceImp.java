package com.collage.library.service;

import com.collage.library.config.CustomNotFoundException;
import com.collage.library.model.Book;
import com.collage.library.model.Loan;
import com.collage.library.model.User;
import com.collage.library.repository.BookRepository;
import com.collage.library.repository.LoanRepository;
import com.collage.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LoanServiceImp implements LoanService{

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Loan> findAllLoans() {
        return StreamSupport.stream(
                loanRepository.findAll().spliterator(),
                false
        )
                .collect(Collectors.toList());
    }

    @Override
    public Loan findLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(()->new CustomNotFoundException("Not Found Loan!!..."));
    }

    @Override
    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan updateLoan(Long id, Loan loan) {
        return loanRepository.findById(id)
                .map(obj->{
                    loan.setDate(loan.getDate());
                    loan.setDateReturn(loan.getDateReturn());
                    return loanRepository.save(obj);
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found Loan!!..."));
    }

    @Override
    public Loan removeLoan(Long id) {
        return loanRepository.findById(id)
                .map(obj->{
                    loanRepository.delete(obj);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found Loan!!..."));
    }

    @Override
    @Transactional
    public Loan addBookToLoan(Long idBook, Long idLoan) {
        return loanRepository.findById(idLoan)
                .map(obj->{
                    Book book=bookRepository.findById(idBook)
                            .orElseThrow(()->new CustomNotFoundException("Not Found Book!!..."));
                    obj.addBook(book);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found Loan!!..."));
    }

    @Override
    @Transactional
    public Loan removeBookFromLoan(Long idBook, Long idLoan) {
        return loanRepository.findById(idLoan)
                .map(obj->{
                    Book book=bookRepository.findById(idBook)
                            .orElseThrow(()->new CustomNotFoundException("Not Found Book!!..."));
                    obj.removeBook(book);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found Loan!!..."));
    }

    @Override
    @Transactional
    public Loan addUserToLoan(Long idUser, Long idLoan) {
        return loanRepository.findById(idLoan)
                .map(obj->{
                    User user=userRepository.findById(idUser)
                            .orElseThrow(()->new CustomNotFoundException("Not Found User!!..."));
                    obj.setUser(user);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found Loan!!..."));
    }

    @Override
    @Transactional
    public Loan removeUserFromLoan(Long idUser, Long idLoan) {
        return loanRepository.findById(idLoan)
                .map(obj->{
                    User user=userRepository.findById(idUser)
                            .orElseThrow(()->new CustomNotFoundException("Not Found User!!..."));
                    obj.setUser(null);
                    return obj;
                })
                .orElseThrow(()->new CustomNotFoundException("Not Found Loan!!..."));
    }
}
