package ru.example.BankCard.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.example.BankCard.entity.Person;
import ru.example.BankCard.exception.NotFoundException;

@Repository
public class EntityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Person findById(Integer id) { //TODO Обсудить функцию, поскольку есть наитие, что я "мудрец" :)
        Person person;
        try {
             person = entityManager.createQuery(
                            "SELECT person FROM Person person JOIN FETCH person.accounts WHERE person.id = ?1", Person.class)
                    .setParameter(1, id).getSingleResult();
            if (person == null) {
                throw new NotFoundException(String.format("Person with id %d does not exist in the database.", id));
            }
        } catch (NoResultException e) {
            throw new NotFoundException(String.format("Person with id %d does not exist in the database.", id));
        }
        return person;
        }
    }

