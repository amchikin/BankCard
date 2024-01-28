package ru.example.BankCard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.BankCard.dto.AccountChangeBalanceDto;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.dto.AccountSaveDto;
import ru.example.BankCard.service.AccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping()
    public List<AccountDto> showAllAccounts() {
        return accountService.getAccountList();
    }

    @PostMapping()
    public String createAccount(@RequestBody @Valid AccountSaveDto accountSaveDto) {
        accountSaveDto = accountService.saveAccountRqDto(accountSaveDto);
        return String.format("New account (bank card) with id %d has been created.", accountSaveDto.getId());
    }

    /**
     * Update salary card by personId. If the balance becomes negative, then a readable exception
     */
    @PostMapping("/update")
    public String updateSalaryBalance(@RequestBody AccountChangeBalanceDto accountChangeBalanceDto) {
        accountService.changeAccountSalaryBalanceRqDto(accountChangeBalanceDto);
        return String.format("The salary card of a person with id %d has been changed to %d",
                accountChangeBalanceDto.getPersonId(), accountChangeBalanceDto.getValue());
    }
}
