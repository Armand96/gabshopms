package com.gaboot.gabshop.user.user;

import com.gaboot.gabshop.grpc.user.CreateUser;
import com.gaboot.gabshop.grpc.user.User;
import com.gaboot.gabshop.grpc.user.UserStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserMapper {
    public User toGRPC(UserEntity ent) {
        return User.newBuilder()
                .setId(ent.getId())
                .setEmail(ent.getEmail())
                .setFirstname(ent.getFirstname())
                .setLastname(ent.getLastname())
                .setPassword(ent.getPassword())
                .setGender(ent.getGender())
                .setStatus(ent.getStatus())
                .setImage(ent.getImage() != null ? ent.getImage() : "")
                .setImageThumb(ent.getImageThumb() != null ? ent.getImageThumb() : "")
                .setCreatedAt(ent.getCreatedAt() != null ? ent.getCreatedAt().getTime() : 0L) // Default to epoch 0
                .setUpdatedAt(ent.getUpdatedAt() != null ? ent.getUpdatedAt().getTime() : 0L) // Default to epoch 0
                .build();
    }

    public UserEntity toEntity(User user) {
        user.getImage();
        user.getImageThumb();
        return new UserEntity()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setPassword(user.getPassword())
                .setGender(user.getGender())
                .setStatus(user.getStatus())
                .setImage(user.getImage())
                .setImageThumb(user.getImageThumb())
                .setCreatedAt(new Date(user.getCreatedAt())) // Default to epoch 0
                .setUpdatedAt(new Date(user.getUpdatedAt()));
    }

    public UserEntity toEntity(CreateUser user) {
        return new UserEntity()
                .setEmail(user.getEmail())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setPassword(user.getPassword())
                .setGender(user.getGender())
                .setStatus(UserStatus.REGISTERED)
                .setImage("")
                .setImageThumb("")
                .setCreatedAt(new Date()) // Default to epoch 0
                .setUpdatedAt(new Date());
    }
}
