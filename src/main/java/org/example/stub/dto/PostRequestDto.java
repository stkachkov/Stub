package org.example.stub.dto;

import jakarta.validation.constraints.NotBlank;

public class PostRequestDto {

    @NotBlank(message = "login is required and cannot be empty")
    private String login;

    @NotBlank(message = "password is required and cannot be empty")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
