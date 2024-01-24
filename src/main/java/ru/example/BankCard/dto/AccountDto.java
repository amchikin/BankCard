package ru.example.BankCard.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;
@Getter
@Setter
public class AccountDto extends AbstractDto {
    @NotEmpty(message = "Поле номера карты не должно быть пустым")
    @Size(min = 16, max = 16, message = "Размер номера карты ровно 16")
    private String cardNumber;
    @Min(value = 0, message = "Баланс не может быть отрицательным")
    private BigInteger balance;
}
