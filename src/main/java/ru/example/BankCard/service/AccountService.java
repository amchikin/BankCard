package ru.example.BankCard.service;
import ru.example.BankCard.dto.AccountChangeBalanceDto;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.dto.AccountSaveDto;
import java.util.List;
public interface AccountService {
    List<AccountDto> findAll();
    public void save(AccountSaveDto accountSaveDto); // TODO модификатор доступа не нужен  // TODO void лучше не использовать
    void changeBalance(AccountChangeBalanceDto accountChangeBalanceDto);
}
