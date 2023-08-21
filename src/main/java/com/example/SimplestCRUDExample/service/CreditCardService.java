package com.example.SimplestCRUDExample.service;

import com.example.SimplestCRUDExample.model.CreditCardRequest;
import com.example.SimplestCRUDExample.model.CreditCardResponse;
import org.springframework.http.ResponseEntity;

public interface CreditCardService {
    ResponseEntity<CreditCardResponse> getCreditCardInfo(CreditCardRequest creditCardRequest);
}
