package com.example.SimplestCRUDExample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreditCardResponse {
    private Long CardNumber;
    private String validity;
}
