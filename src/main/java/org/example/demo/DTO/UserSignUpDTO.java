package org.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUpDTO {

    @Size(min = 3,message = "login must be at least 3 characters")
    @Size(max = 25,message = "login must be less then 26 characters")
    private String login;

    @Size(min = 5,message = "password must be at least 5 characters")
    @Size(max = 25,message = "password must be less then 26 characters")
    private String password;

    @NotBlank(message = "password can not be empty")
    private String rePassword;


}
