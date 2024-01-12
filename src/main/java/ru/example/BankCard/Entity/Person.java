package ru.example.BankCard.Entity;

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
@Table(name="Person")
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

    @Column(name="birthday")
    @Temporal(TemporalType.DATE) // что бы Спринг понял, что это дата
    @DateTimeFormat(pattern = "yyyy-MM-dd") // чтобы авто парсить // Чтобы красиво выводить сообщение об ошибке нужно добавить Спринг-Валидатор(урок 41) TODO
    @NotNull(message = "Data should not be empty")
    private Date birthday;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;

}
