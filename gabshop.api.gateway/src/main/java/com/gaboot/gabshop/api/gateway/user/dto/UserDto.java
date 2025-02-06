package com.gaboot.gabshop.api.gateway.user.dto;

import com.gaboot.gabshop.grpc.user.Gender;
import com.gaboot.gabshop.grpc.user.UserStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserDto {
    private long id;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private Gender gender;
    private UserStatus status;
    private String image;
    private String imageThumb;
    private Date createdAt;
    private Date updatedAt;
}
