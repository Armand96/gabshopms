package com.gaboot.gabshop.api.gateway.user;

import com.gaboot.gabshop.api.gateway.common.dto.ResponseDto;
import com.gaboot.gabshop.api.gateway.user.dto.CreateUserDto;
import com.gaboot.gabshop.api.gateway.user.dto.UpdateUserDto;
import com.gaboot.gabshop.api.gateway.user.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserServiceInterfaceRest {
    public ResponseDto<UserDto> currentUser(long id);
    public ResponseDto<UserDto> activateUSer(long id);
    public ResponseDto<UserDto> deactivateUser(long id);
    public ResponseDto<UserDto> registerUser(CreateUserDto createUserDto, MultipartFile file);
    public ResponseDto<UserDto> updateUser(UpdateUserDto updateUserDto, MultipartFile file);
}