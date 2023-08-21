package com.example.SimplestCRUDExample.service.impl;

import com.example.SimplestCRUDExample.model.CardType;
import com.example.SimplestCRUDExample.model.CreditCardRequest;
import com.example.SimplestCRUDExample.model.CreditCardResponse;
import com.example.SimplestCRUDExample.repo.CreditCardRepo;
import com.example.SimplestCRUDExample.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import static com.example.SimplestCRUDExample.model.CardPlanType.*;

public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    CreditCardRepo creditCardRepo;

    @Override
    public ResponseEntity<CreditCardResponse> getCreditCardInfo(CreditCardRequest creditCardRequest) {
        try {
            Optional<CreditCardRequest> creditCardResponseOptional = creditCardRepo.findById(creditCardRequest.getCustomerId());
            if (creditCardResponseOptional.isPresent()) {
                throw new Exception();
            }
            if (creditCardResponseOptional.isEmpty()) {
                CreditCardRequest creditCardRequest1 = CreditCardRequest.builder()
                        .cardType(creditCardRequest.getCardType())
                        .customerId(creditCardRequest.getCustomerId())
                        .cardPlanType(creditCardRequest.getCardPlanType())
                        .ccv(getCvvNumber())
                        .cardNumber(getCardNumber())
                        .build();
                creditCardRepo.save(creditCardRequest1);
                CreditCardResponse creditCardResponse = CreditCardResponse.builder()
                        .CardNumber(Long.valueOf(creditCardRequest1.getCardNumber()))
                        .validity(getValidity(creditCardRequest1.getCardType()))
                        .build();
                return ResponseEntity.of(Optional.ofNullable(creditCardResponse));
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
        return null;
    }

    private String getValidity(CardType cardType) {
        if(BASIC.equals(cardType)){
            LocalDate currentDate = LocalDate.now();
            LocalDate futureDate = currentDate.plusYears(2);
            return futureDate.toString();
        }
        if(PREMIUM.equals(cardType)){
            LocalDate currentDate = LocalDate.now();
            LocalDate futureDate = currentDate.plusYears(5);
            return futureDate.toString();
        }
        if(SUPER.equals(cardType)){
            LocalDate currentDate = LocalDate.now();
            LocalDate futureDate = currentDate.plusYears(7);
            return futureDate.toString();
        }
    }

    private String getCardNumber() {
        String cardNumber = getRandomCardNumber();
        creditCardRepo.findByCardNumber(cardNumber);
        return cardNumber;
    }

    private String getRandomCardNumber(){
        Random random = new Random();
        int number1 = random.nextInt(9999-1000+1)+1000;
        int number2 = random.nextInt(9999-1000+1)+1000;
        int number3 = random.nextInt(9999-1000+1)+1000;
        int number4 = random.nextInt(9999-1000+1)+1000;
        String cardNumber = Integer.toString(number1).concat(Integer.toString(number2).concat(Integer.toString(number3).concat(Integer.toString(number4))));
return  cardNumber;
    }

    private int getCvvNumber() {
        Random random = new Random();
        int cvvNumber = random.nextInt(999-100+1)+100;
        return cvvNumber;
    }
}
