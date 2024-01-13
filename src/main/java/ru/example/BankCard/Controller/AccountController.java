package ru.example.BankCard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.BankCard.DTO.AccountDTO;
import ru.example.BankCard.DTO.PersonDTO;
import ru.example.BankCard.Service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public List<AccountDTO> getAccounts() {
        return accountService.findAll();
    }


}
