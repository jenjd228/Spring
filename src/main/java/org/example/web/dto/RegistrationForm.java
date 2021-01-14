package org.example.web.dto;

import lombok.*;
import org.example.web.MyPatterns.OnlyLetters;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationForm {

    @NotNull
    @NotEmpty(message = "Must be not empty")
    @Size(min = 2, max = 50,message = "Must be between 2 and 50")
    @OnlyLetters
    private String username;

    @NotNull
    @NotEmpty(message = "Must be not empty")
    @Size(min = 6, max = 20,message = "Must be between 6 and 20")
    private String password;

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
