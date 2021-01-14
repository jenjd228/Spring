package org.example.web.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationForm {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
