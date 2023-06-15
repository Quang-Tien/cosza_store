package com.cybersoft.cozastore.payload.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SignupRequest {
    @NotEmpty(message = "Vui lòng nhập username")
    private String username;
    @NotEmpty(message = "Vui lòng nhập password")
    @NotNull(message = "Password not null")
    @Length(min = 4)
    private String password;

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Vui lòng nhập email")
    @NotNull(message = "Email not null")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
