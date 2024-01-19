package ru.example.BankCard.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Getter
@Setter
public class PersonDto extends AbstractDto {

    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    @NotEmpty(message = "Surname should not be empty")
    private String surname;

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data should not be empty")
    private Date birthday;

}
