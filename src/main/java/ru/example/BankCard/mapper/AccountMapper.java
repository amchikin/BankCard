package ru.example.BankCard.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.entity.Account;


@Component
public class AccountMapper extends AbstractMapper<Account, AccountDto> {
    @Autowired
    public AccountMapper() {super(Account.class, AccountDto.class); }
}

