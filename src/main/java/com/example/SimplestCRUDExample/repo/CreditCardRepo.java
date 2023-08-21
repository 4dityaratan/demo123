package com.example.SimplestCRUDExample.repo;

import com.example.SimplestCRUDExample.model.CreditCardRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepo extends JpaRepository<CreditCardRequest, String> {
    String findByCardNumber(String cardNumber);
}
