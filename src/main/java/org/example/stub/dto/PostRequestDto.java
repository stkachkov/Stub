package org.example.stub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

    @NotBlank(message = "login is required and cannot be empty")
    private String login;

    @NotBlank(message = "password is required and cannot be empty")
    private String password;
}
