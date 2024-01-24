package ru.example.BankCard.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;
@Getter
@Setter
@Entity
@Table(name="Account")
public class Account extends AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Поле номера карты не должно быть пустым")
    @Size(min = 16, max = 16, message = "Размер номера карты ровно 16")
    @Column(name = "cardnumber")
    private String cardNumber;
    @Min(value = 0, message = "Баланс не может быть отрицательным")
    @Column(name = "balance")
    private BigInteger balance;
    @Column(name="issalary")
    private Boolean isSalary;
    @ManyToOne
    @JoinColumn(name = "personid", referencedColumnName = "id")  // TODO personId rename
    private Person owner;
}




