package com.gaboot.gabshop.api.gateway.user;

import com.gaboot.gabshop.api.gateway.common.dto.ResponseDto;
import com.gaboot.gabshop.api.gateway.user.dto.CreateUserDto;
import com.gaboot.gabshop.api.gateway.user.dto.UpdateUserDto;
import com.gaboot.gabshop.api.gateway.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserServiceInterfaceRest userServiceRest;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> currentUser(@PathVariable long id) {
        return ResponseEntity.ok(userServiceRest.currentUser(id));
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<UserDto>> registerUser(CreateUserDto createUserDto) {
        return ResponseEntity.ok(userServiceRest.registerUser(createUserDto, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> updateUser(
            @RequestPart("user") UpdateUserDto updateUserDto,
            @RequestPart("file") MultipartFile file
            ) {
        return ResponseEntity.ok(userServiceRest.updateUser(updateUserDto, file));
    }

    @GetMapping("/activateUser/{id}")
    public ResponseEntity<ResponseDto<UserDto>> activateUser(@PathVariable long id) {
        return ResponseEntity.ok(userServiceRest.activateUSer(id));
    }

    @GetMapping("/deactivateUser/{id}")
    public ResponseEntity<ResponseDto<UserDto>> deactivateUser(@PathVariable long id) {
        return ResponseEntity.ok(userServiceRest.deactivateUser(id));
    }
}
