package ru.example.BankCard.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.BankCard.dto.*;
import ru.example.BankCard.entity.Account;
import ru.example.BankCard.exception.AccountChangeBalanceException;
import ru.example.BankCard.exception.NotFoundException;
import ru.example.BankCard.mapper.*;

import ru.example.BankCard.repository.AccountsRepository;
import ru.example.BankCard.service.AccountService;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;
    private final ShowAllAccountMapper showAllAccountMapper;
    private final AccountSaveRequestMapper accountSaveRequestMapper;
    private final AccountChangeBalanceRequestMapper accountChangeBalanceRequestMapper;
    private final AccountSaveResponseMapper accountSaveResponseMapper;
    private final AccountMapper accountMapper;
    private final AccountChangeBalanceResponseMapper accountChangeBalanceResponseMapper;

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
    public AccountChangeBalanceResponseDto changeAccountSalaryBalanceRequestDto(AccountChangeBalanceRequestDto accountChangeBalanceRequestDto)
            throws AccountChangeBalanceException {
        Account account = accountsRepository.findAccountsByOwnerAndAndIsSalaryTrue(accountChangeBalanceRequestMapper.map(accountChangeBalanceRequestDto).getOwner());
        BigInteger newBalance = account.getBalance().add(accountChangeBalanceRequestMapper.map(accountChangeBalanceRequestDto).getBalance());
        if (newBalance.signum() == 1 || newBalance.signum() == 0) {
            account.setBalance(newBalance);
            accountsRepository.save(account);
            return accountChangeBalanceResponseMapper.map(account);
        } else throw new AccountChangeBalanceException("The balance cannot be negative");
    }

    @Override
    public ShowAllAccountDto getAccountByIdOrThrow(Integer id) {
        return accountMapper.map(accountsRepository.findById(id).
                orElseThrow(() ->
                        new NotFoundException(
                                String.format("Account with id %d does not exist in the database.", id))));
    }

    @Override
    public UpdateAccountDto updateAccountById(UpdateAccountDto updateAccountDto, Integer id) {
        Account account = accountsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Account with id %d does not exist in the database.", id)));
        account.setCardNumber(updateAccountDto.getCardNumber());
        account.setBalance(updateAccountDto.getBalance());
        account.setIsSalary(updateAccountDto.getIsSalary());
        accountsRepository.save(account);
        return updateAccountDto;
    }
}
