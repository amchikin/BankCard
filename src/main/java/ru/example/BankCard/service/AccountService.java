package ru.example.BankCard.service;

import ru.example.BankCard.dto.AccountChangeBalanceDto;
import ru.example.BankCard.dto.ShowAllAccountDto;
import ru.example.BankCard.dto.AccountSaveRequestDto;
import ru.example.BankCard.dto.AccountSaveResponseDto;

import java.util.List;

public interface AccountService {
    List<ShowAllAccountDto> getAccountList();

    AccountSaveResponseDto saveAccountRequestDto(AccountSaveRequestDto accountSaveRequestDto);

    void changeAccountSalaryBalanceRequestDto(AccountChangeBalanceDto accountChangeBalanceDto); // TODO уместен ли void
}
