package ru.example.BankCard.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShowCardsDto {
    private String fio;
    private List<AccountDto> accounts;
}
