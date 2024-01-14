package ru.example.BankCard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.BankCard.entity.Account;

public interface  AccountsRepository extends JpaRepository<Account, Integer> {
}
