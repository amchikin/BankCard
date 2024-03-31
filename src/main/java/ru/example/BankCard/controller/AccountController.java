package ru.example.BankCard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.BankCard.dto.*;
import ru.example.BankCard.service.AccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping()
    public List<ShowAllAccountDto> showAllAccounts() {
        return accountService.getAccountList();
    }

    @PostMapping()
    public ResponseEntity<AccountSaveResponseDto> createAccount(@RequestBody @Valid AccountSaveRequestDto accountSaveRequestDto) {
        return new ResponseEntity<>(accountService.saveAccountRequestDto(accountSaveRequestDto), HttpStatus.CREATED);
    }

    /**
     * Update salary card by personId. If the balance becomes negative, then a readable exception
     */
    @PostMapping("/update")
    public ResponseEntity<AccountChangeBalanceResponseDto> updateSalaryBalance(@RequestBody AccountChangeBalanceRequestDto accountChangeBalanceRequestDto) {
        return new ResponseEntity<>(accountService.changeAccountSalaryBalanceRequestDto(accountChangeBalanceRequestDto), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ShowAllAccountDto showAccount(@PathVariable("id") Integer id) {
        return accountService.getAccountByIdOrThrow(id);
    }

    @PatchMapping("/{id}")
    public UpdateAccountDto updatePerson(@RequestBody @Valid UpdateAccountDto updateAccountDto, @PathVariable("id") Integer id) {
        return accountService.updateAccountById(updateAccountDto, id);
    }
}
