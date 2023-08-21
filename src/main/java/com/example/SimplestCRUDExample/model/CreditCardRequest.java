package com.example.SimplestCRUDExample.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreditCardRequest {
    private String customerId;
    private CardType cardType;
    private CardPlanType cardPlanType;
    private String cardNumber;
    private int ccv;
}
