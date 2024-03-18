package ru.example.BankCard.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.BankCard.entity.Person;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
    @EntityGraph(attributePaths = "accounts")
    Optional<Person> findById(Integer id);

}
