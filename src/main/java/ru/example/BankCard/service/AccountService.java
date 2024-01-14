package ru.example.BankCard.service;

import ru.example.BankCard.dto.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> findAll();

}
