package ru.example.BankCard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.entity.Account;

import ru.example.BankCard.mapper.AccountMapper;
import ru.example.BankCard.repository.AccountsRepository;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountsRepository accountsRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountDto> findAll() {  // TODO Подумать над лучшей реализацией
        List<AccountDto> listAccountDTO = new ArrayList<>();
        List<Account> listAccount = accountsRepository.findAll();
        listAccount.forEach(element -> listAccountDTO.add(accountMapper.destinationToSource(element)));
        return listAccountDTO;
    }


}
