package ru.example.BankCard.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class AccountChangeBalanceDto {
    private Integer personId;
    private BigInteger value;
}
