package ru.example.BankCard.Service;

import ru.example.BankCard.DTO.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> findAll();

}
