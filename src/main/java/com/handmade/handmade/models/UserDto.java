package com.handmade.handmade.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @NotEmpty(message = "Ведіть ім'\''я")
    private String firstName;
    @NotEmpty(message = "Ведіть прізвище")
    private String lastName;
    @NotEmpty(message = "Логін не може бути пустим")
    @Email
    private String email;
    @NotEmpty(message = "Пароль не може бути пустим")
    private String password;
}