package com.collage.library.controller;

import com.collage.library.dto.LoanDto;
import com.collage.library.model.Loan;
import com.collage.library.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanDto>> findAll(){
        return ResponseEntity.ok(
                loanService.findAllLoans()
                        .stream()
                        .map(LoanDto::from)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<LoanDto> findLoanById(@PathVariable Long id){
        return ResponseEntity.ok(
                LoanDto.from(loanService.findLoanById(id))
        );
    }

    @PostMapping
    public ResponseEntity<LoanDto> saveLoan(@Valid @RequestBody LoanDto loanDto){
        return new ResponseEntity<>(
                LoanDto.from(
                        loanService.saveLoan(Loan.from(loanDto))
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/edi/{id}")
    public ResponseEntity<LoanDto> updateLoan(@PathVariable Long id, @Valid @RequestBody LoanDto loanDto){
        return ResponseEntity.ok(
                LoanDto.from(loanService.updateLoan(id,Loan.from(loanDto)))
        );
    }

    @DeleteMapping("/dell")
    public ResponseEntity<LoanDto> dellLoan(@RequestParam("id") Long id){
        return ResponseEntity.ok(
                LoanDto.from(
                        loanService.removeLoan(id)
                )
        );
    }

    @PostMapping("/add/{idBook}/{idLoan}")
    public ResponseEntity<LoanDto> addBookToLoan(@PathVariable Long idBook, @PathVariable Long idLoan){
        return ResponseEntity.ok(
                LoanDto.from(
                        loanService.addBookToLoan(idBook, idLoan)
                )
        );
    }

    @DeleteMapping("/remove/{idBook}/{idLoan}")
    public ResponseEntity<LoanDto> dellBookFromLoan(@PathVariable Long idBook, @PathVariable Long idLoan){
        return ResponseEntity.ok(
                LoanDto.from(
                        loanService.removeBookFromLoan(idBook, idLoan)
                )
        );
    }

    @PostMapping("/add/user/{idUser}/{idLoan}")
    public ResponseEntity<LoanDto> addUserToLoan(@PathVariable Long idUser, @PathVariable Long idLoan){
        return ResponseEntity.ok(
                LoanDto.from(
                        loanService.addUserToLoan(idUser, idLoan)
                )
        );
    }

    @DeleteMapping("/remove/user/{idUser}/{idLoan}")
    public ResponseEntity<LoanDto> dellUserFromLoan(@PathVariable Long idUser, @PathVariable Long idLoan){
        return ResponseEntity.ok(
                LoanDto.from(
                        loanService.removeUserFromLoan(idUser, idLoan)
                )
        );
    }

}
