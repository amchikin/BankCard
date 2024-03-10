package ru.example.BankCard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.BankCard.dto.AccountChangeBalanceDto;
import ru.example.BankCard.dto.ShowAllAccountDto;
import ru.example.BankCard.dto.AccountSaveRequestDto;
import ru.example.BankCard.dto.AccountSaveResponseDto;
import ru.example.BankCard.entity.Account;
import ru.example.BankCard.exception.AccountChangeBalanceException;
import ru.example.BankCard.mapper.AccountChangeBalanceMapper;
import ru.example.BankCard.mapper.ShowAllAccountMapper;
import ru.example.BankCard.mapper.AccountSaveRequestMapper;

import ru.example.BankCard.mapper.AccountSaveResponseMapper;
import ru.example.BankCard.repository.AccountsRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;
    private final ShowAllAccountMapper showAllAccountMapper;
    private final AccountSaveRequestMapper accountSaveRequestMapper;
    private final AccountChangeBalanceMapper accountChangeBalanceMapper;
    private final AccountSaveResponseMapper accountSaveResponseMapper;

    @Override
    public List<ShowAllAccountDto> getAccountList() {
        return accountsRepository.findAll().stream().map(showAllAccountMapper::map).collect(Collectors.toList());
    }

    @Override
    public AccountSaveResponseDto saveAccountRequestDto(AccountSaveRequestDto accountSaveRequestDto) {
        return accountSaveResponseMapper.map(accountsRepository.
                save(accountSaveRequestMapper.map(accountSaveRequestDto)));
    }

    @Override
    public void changeAccountSalaryBalanceRequestDto(AccountChangeBalanceDto accountChangeBalanceDto)
            throws AccountChangeBalanceException {
        Account account = accountsRepository.findAccountsByOwnerAndAndIsSalaryTrue(accountChangeBalanceMapper.map(accountChangeBalanceDto).getOwner());
        BigInteger newBalance = account.getBalance().add(accountChangeBalanceMapper.map(accountChangeBalanceDto).getBalance());
        if (newBalance.signum() == 1 || newBalance.signum() == 0) {
            account.setBalance(newBalance);
            accountsRepository.save(account);
        } else throw new AccountChangeBalanceException("The balance cannot be negative");
    }
}
