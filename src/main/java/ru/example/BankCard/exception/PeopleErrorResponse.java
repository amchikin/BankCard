package ru.example.BankCard.exception;

import lombok.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
@AllArgsConstructor//TODO проверить без него
@Builder
public class PeopleErrorResponse {
    private String message;
    private long timestamp;

    public static void CreateErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreateException(errorMsg.toString());
        }
    }
}
