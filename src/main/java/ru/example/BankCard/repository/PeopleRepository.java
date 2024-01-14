package ru.example.BankCard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.BankCard.entity.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
