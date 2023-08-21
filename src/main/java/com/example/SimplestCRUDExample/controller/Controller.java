package com.example.SimplestCRUDExample.controller;

import com.example.SimplestCRUDExample.model.CreditCardRequest;
import com.example.SimplestCRUDExample.model.CreditCardResponse;
import com.example.SimplestCRUDExample.repo.CreditCardRepo;
import com.example.SimplestCRUDExample.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    CreditCardRepo creditCardRepo;

    @Autowired
    CreditCardService creditCardService;

    @PostMapping("/cc/generate")
    public ResponseEntity<CreditCardResponse> addCreditCard(@RequestBody CreditCardRequest creditCardRequest){
        try{
            return new ResponseEntity<>(creditCardService.getCreditCardInfo(creditCardRequest).getBody(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
