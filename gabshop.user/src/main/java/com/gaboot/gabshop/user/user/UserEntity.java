package com.gaboot.gabshop.user.user;

import com.gaboot.gabshop.grpc.user.Gender;
import com.gaboot.gabshop.grpc.user.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Entity
@Table(name = "users")
@ToString @Getter @Setter
@Accessors(chain = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String firstname;
    private String lastname;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('MALE', 'FEMALE')")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('REGISTERED', 'ACTIVATED', 'BANNED', 'INACTIVE')")
    private UserStatus status;

    private String image;
    private String imageThumb;

    private Date createdAt;
    private Date updatedAt;
}
