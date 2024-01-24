package ru.example.BankCard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.BankCard.dto.AccountChangeBalanceDto;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.dto.AccountSaveDto;
import ru.example.BankCard.entity.Account;
import ru.example.BankCard.exception.AccountChangeBalanceException;
import ru.example.BankCard.mapper.AccountChangeBalanceMapper;
import ru.example.BankCard.mapper.AccountMapper;
import ru.example.BankCard.mapper.AccountSaveMapper;

import ru.example.BankCard.repository.AccountsRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;
    private final AccountSaveMapper accountSaveMapper;
    private final AccountChangeBalanceMapper accountChangeBalanceMapper;

    @Override
    public List<AccountDto> findAll() {  // TODO Подумать над лучшей реализацией
        List<AccountDto> listAccountDTO = new ArrayList<>();
        List<Account> listAccount = accountsRepository.findAll();
        listAccount.forEach(element -> listAccountDTO.add(accountMapper.map(element)));
        return listAccountDTO;
    }

    @Override
    public AccountSaveDto save(AccountSaveDto accountSaveDto) {
        return accountSaveMapper.map(accountsRepository.save(accountSaveMapper.map(accountSaveDto)));
    }

    @Override
    public void changeBalance(AccountChangeBalanceDto accountChangeBalanceDto)
            throws AccountChangeBalanceException {
        List<Account> accountList = accountsRepository.findByOwner(
                accountChangeBalanceMapper.map(accountChangeBalanceDto).getOwner());
        accountList.sort(Comparator.comparing(Account::getIsSalary)); //TODO Как нормально получить зарплатный аккаунт??? Пока сделал хрень, но рабочую)
        BigInteger newBalance = accountList.get(accountList.size() - 1).getBalance().
                add(accountChangeBalanceMapper.map(accountChangeBalanceDto).getBalance());
        if (newBalance.signum() == 1 || newBalance.signum() == 0) {
            accountList.get(accountList.size() - 1).setBalance(newBalance);
            accountsRepository.save(accountList.get(accountList.size() - 1));
        } else throw new AccountChangeBalanceException();
    }
}
