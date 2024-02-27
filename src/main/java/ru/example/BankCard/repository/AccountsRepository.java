package ru.example.BankCard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.BankCard.entity.Account;
import ru.example.BankCard.entity.Person;

import java.util.List;

public interface AccountsRepository extends JpaRepository<Account, Integer> {
    List<Account> findByOwner(Person person);
    Account findAccountsByOwnerAndAndIsSalaryTrue(Person person);
}
