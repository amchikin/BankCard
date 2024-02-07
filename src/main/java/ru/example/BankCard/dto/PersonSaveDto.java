package ru.example.BankCard.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonSaveDto {

    private Integer id;

    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    @NotEmpty(message = "Surname should not be empty")
    private String surname;

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data should not be empty")
    private LocalDate birthday;
}
