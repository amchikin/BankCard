package ru.example.BankCard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.dto.AccountSaveDto;
import ru.example.BankCard.dto.PersonDto;
import ru.example.BankCard.entity.Account;

import ru.example.BankCard.entity.Person;
import ru.example.BankCard.mapper.AccountMapper;
import ru.example.BankCard.mapper.AccountSaveMapper;
import ru.example.BankCard.mapper.AccountSaveMapperInjectService;
import ru.example.BankCard.repository.AccountsRepository;
import ru.example.BankCard.repository.PeopleRepository;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountsRepository accountsRepository;
    private final PeopleRepository peopleRepository;
    private final AccountMapper accountMapper;
    private final AccountSaveMapperInjectService accountSaveMapperInjectService;

    @Override
    public List<AccountDto> findAll() {  // TODO Подумать над лучшей реализацией
        List<AccountDto> listAccountDTO = new ArrayList<>();
        List<Account> listAccount = accountsRepository.findAll();
        listAccount.forEach(element -> listAccountDTO.add(accountMapper.toDto(element)));
        return listAccountDTO;
    }

    @Override
    public void save(AccountSaveDto accountSaveDto) {
        //return accountSaveMapper.toDto(accountsRepository.save(accountSaveMapper.toModel(accountSaveDto)));
        accountsRepository.save(accountSaveMapperInjectService.ToModel(accountSaveDto));
    }


}
