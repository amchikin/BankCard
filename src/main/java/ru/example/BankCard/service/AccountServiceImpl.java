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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;
    private final AccountSaveMapper accountSaveMapper;
    private final AccountChangeBalanceMapper accountChangeBalanceMapper;

    @Override
    public List<AccountDto> getAccountList() {
        return accountsRepository.findAll().stream().map(accountMapper::map).collect(Collectors.toList());
    }

    @Override
    public AccountSaveDto saveAccountRqDto(AccountSaveDto accountSaveDto) {
        return accountSaveMapper.map(accountsRepository.save(accountSaveMapper.map(accountSaveDto)));
    }

    @Override
    public void changeAccountSalaryBalanceRqDto(AccountChangeBalanceDto accountChangeBalanceDto)
            throws AccountChangeBalanceException {
        Account account = accountsRepository.findAccountsByOwnerAndAndIsSalaryTrue(accountChangeBalanceMapper.map(accountChangeBalanceDto).getOwner());
        BigInteger newBalance = account.getBalance().add(accountChangeBalanceMapper.map(accountChangeBalanceDto).getBalance());
        if (newBalance.signum() == 1 || newBalance.signum() == 0) {
            account.setBalance(newBalance);
            accountsRepository.save(account);
        } else throw new AccountChangeBalanceException("The balance cannot be negative");
    }
}
