package ru.example.BankCard.service;

import ru.example.BankCard.dto.*;

import java.util.List;

public interface AccountService {
    List<ShowAllAccountDto> getAccountList();

    AccountSaveResponseDto saveAccountRequestDto(AccountSaveRequestDto accountSaveRequestDto);

    void changeAccountSalaryBalanceRequestDto(AccountChangeBalanceDto accountChangeBalanceDto); // TODO Спросить: уместен ли void?

    AccountDto getAccountByIdOrThrow(Integer id);

    UpdateAccountDto updateAccountById(UpdateAccountDto updateAccountDto, Integer id);
}
