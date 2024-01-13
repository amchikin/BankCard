package ru.example.BankCard.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.example.BankCard.DTO.AccountDTO;
import ru.example.BankCard.Entity.Account;


@Component
public class AccountMapper extends AbstractMapper<Account, AccountDTO> {
    @Autowired
    public AccountMapper() {super(Account.class, AccountDTO.class); }
}

