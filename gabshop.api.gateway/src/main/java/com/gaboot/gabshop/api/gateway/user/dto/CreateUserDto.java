package com.gaboot.gabshop.api.gateway.user.dto;

import com.gaboot.gabshop.grpc.user.Gender;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUserDto {
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    Gender gender;
}
