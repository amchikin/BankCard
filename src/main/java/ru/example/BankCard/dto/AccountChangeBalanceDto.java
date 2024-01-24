package ru.example.BankCard.dto;
import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;
@Getter
@Setter
public class AccountChangeBalanceDto extends AbstractDto {
    private Integer personId;
    private BigInteger value;
}
