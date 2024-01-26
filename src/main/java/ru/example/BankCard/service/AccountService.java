package ru.example.BankCard.service;

import ru.example.BankCard.dto.AccountChangeBalanceDto;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.dto.AccountSaveDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAccountList();

    AccountSaveDto saveAccountRqDto(AccountSaveDto accountSaveDto);

    void changeAccountSalaryBalanceRqDto(AccountChangeBalanceDto accountChangeBalanceDto); // TODO уместен ли void
}
