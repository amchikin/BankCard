package ru.example.BankCard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.BankCard.dto.AccountChangeBalanceDto;
import ru.example.BankCard.dto.ShowAllAccountDto;
import ru.example.BankCard.dto.AccountSaveRequestDto;
import ru.example.BankCard.dto.AccountSaveResponseDto;
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
    public  ResponseEntity<AccountChangeBalanceDto> updateSalaryBalance(@RequestBody AccountChangeBalanceDto accountChangeBalanceDto) {
        accountService.changeAccountSalaryBalanceRequestDto(accountChangeBalanceDto);
        return new ResponseEntity<>(accountChangeBalanceDto, HttpStatus.OK);
    }
//    @PostMapping("/update")
//    public String updateSalaryBalance(@RequestBody AccountChangeBalanceDto accountChangeBalanceDto) {
//        accountService.changeAccountSalaryBalanceRequestDto(accountChangeBalanceDto);
//        return String.format("The salary card of a person with id %d has been changed to %d",
//                accountChangeBalanceDto.getPersonId(), accountChangeBalanceDto.getValue());
//    }
}
