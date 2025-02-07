package com.gaboot.gabshop.api.gateway.user;

import com.gaboot.gabshop.api.gateway.common.dto.ResponseDto;
import com.gaboot.gabshop.api.gateway.common.exception.MapException;
import com.gaboot.gabshop.api.gateway.common.services.MappingService;
import com.gaboot.gabshop.api.gateway.user.dto.CreateUserDto;
import com.gaboot.gabshop.api.gateway.user.dto.UpdateUserDto;
import com.gaboot.gabshop.api.gateway.user.dto.UserDto;
import com.gaboot.gabshop.grpc.user.CreateUser;
import com.gaboot.gabshop.grpc.user.UpdateUser;
import com.gaboot.gabshop.grpc.user.UsersServiceGrpc.UsersServiceBlockingStub;
import com.google.protobuf.Int64Value;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceRest implements UserServiceInterfaceRest {

    private final MapException mapException = new MapException();

    @GrpcClient("userService")
    private UsersServiceBlockingStub userStub;

    @Autowired
    private UserMapperRest mapper;

    @Autowired
    private MappingService<UserDto> mapServ;

    @Override
    public ResponseDto<UserDto> currentUser(long id) {
        try {
            final Int64Value request = Int64Value.newBuilder().setValue(id).build();
            final UserDto user = mapper.toDto(userStub.currentUser(request));
            final ResponseDto<UserDto> respDto = new ResponseDto<>();
            mapServ.mapResponseSuccess(respDto, user, "");
            return respDto;
        } catch (StatusRuntimeException e) {
            throw mapException.mapGrpcException(e);
        }
    }

    @Override
    public ResponseDto<UserDto> activateUSer(long id) {
        try {
            final Int64Value request = Int64Value.newBuilder().setValue(id).build();
            final UserDto user = mapper.toDto(userStub.activateUser(request));
            final ResponseDto<UserDto> respDto = new ResponseDto<>();
            mapServ.mapResponseSuccess(respDto, user, "");
            return respDto;
        } catch (StatusRuntimeException e) {
            throw mapException.mapGrpcException(e);
        }
    }

    @Override
    public ResponseDto<UserDto> deactivateUser(long id) {
        try {
            final Int64Value request = Int64Value.newBuilder().setValue(id).build();
            final UserDto user = mapper.toDto(userStub.deactivateUser(request));
            final ResponseDto<UserDto> respDto = new ResponseDto<>();
            mapServ.mapResponseSuccess(respDto, user, "");
            return respDto;
        } catch (StatusRuntimeException e) {
            throw mapException.mapGrpcException(e);
        }
    }

    @Override
    public ResponseDto<UserDto> registerUser(CreateUserDto createUserDto, MultipartFile file) {
        try {
            final CreateUser req = mapper.toCreateUserGRPC(createUserDto, file);
            final UserDto user = mapper.toDto(userStub.registerUser(req));
            final ResponseDto<UserDto> respDto = new ResponseDto<>();
            mapServ.mapResponseSuccess(respDto, user, "");
            return respDto;
        } catch (StatusRuntimeException e) {
            throw mapException.mapGrpcException(e);
        }
    }

    @Override
    public ResponseDto<UserDto> updateUser(UpdateUserDto updateUserDto, MultipartFile file) {
        try {
            final UpdateUser req = mapper.toUpdateUserGRPC(updateUserDto, file);
            final UserDto user = mapper.toDto(userStub.updateCurrentUser(req));
            final ResponseDto<UserDto> respDto = new ResponseDto<>();
            mapServ.mapResponseSuccess(respDto, user, "");
            return respDto;
        } catch (StatusRuntimeException e) {
            throw mapException.mapGrpcException(e);
        }
    }
}
