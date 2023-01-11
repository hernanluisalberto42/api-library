package com.collage.library.repository;

import com.collage.library.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan,Long> {
       public Optional<Loan> findByUser_IdUser(Long idUser);
}
