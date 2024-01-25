package ru.example.BankCard.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    @NotEmpty(message = "Name should not be empty")
    @Column(name = "surname")
    private String surname;
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @NotEmpty(message = "Name should not be empty")
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE) // TODO можно ли без неё
    @DateTimeFormat(pattern = "yyyy-MM-dd") // TODO Add Spring Validator
    @NotNull(message = "Data should not be empty")
    private Date birthday;
    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;
}
