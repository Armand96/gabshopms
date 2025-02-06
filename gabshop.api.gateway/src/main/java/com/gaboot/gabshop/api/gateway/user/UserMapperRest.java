package com.gaboot.gabshop.api.gateway.user;

import com.gaboot.gabshop.api.gateway.user.dto.CreateUserDto;
import com.gaboot.gabshop.api.gateway.user.dto.UpdateUserDto;
import com.gaboot.gabshop.api.gateway.user.dto.UserDto;
import com.gaboot.gabshop.grpc.user.CreateUser;
import com.gaboot.gabshop.grpc.user.UpdateUser;
import com.gaboot.gabshop.grpc.user.User;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Component
public class UserMapperRest {
    public UserDto toDto(User user) {
        UserDto data = new UserDto();
        return data.setId(user.getId())
                .setEmail(user.getEmail())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setPassword(user.getPassword())
                .setGender(user.getGender())
                .setStatus(user.getStatus())
                .setImage(user.getImage())
                .setImageThumb(user.getImageThumb())
                .setCreatedAt(new Date(user.getCreatedAt())) // Default to epoch 0
                .setUpdatedAt(new Date(user.getUpdatedAt())); // Default to epoch 0
    }

    public User toGRPC(UserDto dto) {
        return User.newBuilder()
                .setEmail(dto.getEmail())
                .setFirstname(dto.getFirstname() != null ? dto.getFirstname() : "")
                .setLastname(dto.getLastname() != null ? dto.getLastname() : "")
                .setPassword(dto.getPassword() != null ? dto.getPassword() : "")
                .setGender(dto.getGender())
                .setStatus(dto.getStatus())
                .setImage(dto.getImage() != null ? dto.getImage() : "")
                .setImageThumb(dto.getImageThumb() != null ? dto.getImageThumb() : "")
                .setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt().getTime() : 0L) // Default to epoch 0
                .setUpdatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt().getTime() : 0L) // Default to epoch 0
                .build();
    }

    public CreateUser toCreateUserGRPC(CreateUserDto createUser, MultipartFile file) {
        return CreateUser.newBuilder()
                .setEmail(createUser.getEmail())
                .setFirstname(createUser.getFirstname())
                .setLastname(createUser.getLastname())
                .setPassword(createUser.getPassword())
                .setGender(createUser.getGender())
                .build();
    }

    public UpdateUser toUpdateUserGRPC(UpdateUserDto updateUser, MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            filename = updateUser.getFirstname().replace(" ", "_").toLowerCase()
                    + "_" + updateUser.getLastname().replace(" ", "_").toLowerCase()
                    + "." + filename.substring(filename.lastIndexOf(".") + 1);
            return UpdateUser.newBuilder()
                    .setUser(
                        CreateUser.newBuilder().setImageContent(ByteString.copyFrom(file.getBytes()))
                        .setEmail(updateUser.getEmail())
                        .setFirstname(updateUser.getFirstname())
                        .setLastname(updateUser.getLastname())
                        .setPassword(updateUser.getPassword())
                        .setGender(updateUser.getGender())
                        .setImageName(filename)
                        .setImageType(filename.substring(filename.lastIndexOf(".") +1))
                        .build()
                    ).setId(updateUser.getId())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
