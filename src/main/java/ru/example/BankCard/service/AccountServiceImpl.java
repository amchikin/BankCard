package ru.example.BankCard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.BankCard.dto.AccountChangeBalanceDto;
import ru.example.BankCard.dto.AccountDto;
import ru.example.BankCard.dto.AccountSaveDto;
import ru.example.BankCard.entity.Account;

import ru.example.BankCard.mapper.AccountChangeBalanceMapper;
import ru.example.BankCard.mapper.AccountChangeBalanceMapperUsingInjectedService;
import ru.example.BankCard.mapper.AccountMapper;

import ru.example.BankCard.mapper.AccountSaveMapperInjectService;
import ru.example.BankCard.repository.AccountsRepository;
import ru.example.BankCard.repository.PeopleRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountsRepository accountsRepository;
    private final PeopleRepository peopleRepository;
    private final AccountMapper accountMapper;
    private final AccountSaveMapperInjectService accountSaveMapperInjectService;
    private final AccountChangeBalanceMapperUsingInjectedService accountChangeBalanceMapperUsingInjectedService;
    private final AccountChangeBalanceMapper accountChangeBalanceMapper;
    @Override
    public List<AccountDto> findAll() {  // TODO Подумать над лучшей реализацией
        List<AccountDto> listAccountDTO = new ArrayList<>();
        List<Account> listAccount = accountsRepository.findAll();
        listAccount.forEach(element -> listAccountDTO.add(accountMapper.toDto(element)));
        return listAccountDTO;
    }

    @Override
    public void save(AccountSaveDto accountSaveDto) {
        accountsRepository.save(accountSaveMapperInjectService.ToModel(accountSaveDto));
    }

    @Override
    public void changeBalance(AccountChangeBalanceDto accountChangeBalanceDto) {
            //accountsRepository.findByOwner(accountChangeBalanceMapper.
        // toModel(accountChangeBalanceDto).getOwner()).
        // setBalance(accountChangeBalanceMapper.
        // toModel(accountChangeBalanceDto).getBalance());
        List<Account> accountList = accountsRepository.findByOwner(
                accountChangeBalanceMapperUsingInjectedService.toModel(accountChangeBalanceDto).getOwner());
        accountList.sort(Comparator.comparing(Account::getIsSalary)); //TODO Как нормально получить зарплатный аккаунт??? Пока сделал хрень, но рабочую)
        BigInteger newBalance = accountList.get(accountList.size()-1).getBalance().
                add(accountChangeBalanceMapper.toModel(accountChangeBalanceDto).getBalance());
        accountList.get(accountList.size()-1).setBalance(newBalance); // Логику переделать!!!
        accountsRepository.save(accountList.get(accountList.size()-1));
//        accountList.get(0).setBalance(
//                accountChangeBalanceMapper.toModel(accountChangeBalanceDto));
//        accountList.get(0).setBalance(accountChangeBalanceMapper.
//                toModel(accountChangeBalanceDto).setBalance(accountChangeBalanceDto.));





    }


}
