package ru.example.BankCard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class  AccountErrorResponse {

    private String message;
    private long timestamp;

    public static void CreateErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors){
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new AccountNotCreateException(errorMsg.toString());
        }
    }
}
