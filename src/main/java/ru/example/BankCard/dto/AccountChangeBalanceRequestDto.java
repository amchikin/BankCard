package ru.example.BankCard.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class AccountChangeBalanceRequestDto {

    private Integer personId;

    private BigInteger value;
}
