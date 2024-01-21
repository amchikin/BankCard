package ru.example.BankCard.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.BankCard.dto.AccountChangeBalanceDto;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.dto.AccountSaveDto;
import ru.example.BankCard.exception.AccountErrorResponse;
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

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid AccountSaveDto accountSaveDto,
                                             BindingResult bindingResult) {
        AccountErrorResponse.CreateErrors(bindingResult);
        accountService.save(accountSaveDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/change_balance")
    public ResponseEntity<HttpStatus> changeBalance(@RequestBody AccountChangeBalanceDto accountChangeBalanceDto) {
        accountService.changeBalance(accountChangeBalanceDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
