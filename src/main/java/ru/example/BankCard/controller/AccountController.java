package ru.example.BankCard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.service.AccountService;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping()
    public List<AccountDto> getAccounts() {
        return accountService.findAll();
    }

    // Add


}
