package com.gaboot.gabshop.user.user;

import com.gaboot.exception.GrpcResourceNotFoundException;
import com.gaboot.gabshop.grpc.user.*;
import com.gaboot.gabshop.grpc.user.UsersServiceGrpc.UsersServiceImplBase;
import com.gaboot.services.files.FileServiceFactory;
import com.google.protobuf.Int64Value;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gaboot.services.images.ImageService;
import org.springframework.beans.factory.annotation.Value;

@GrpcService
public class UserService extends UsersServiceImplBase {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    private ImageService imgSvc;

    @Value("${bucket.url}")
    private String bucketUrl;

    @Value("${bucket.key}")
    private String bucketKey;

    @Value("${bucket.user}")
    private String bucketUser;

    @Value("${bucket.name}")
    private String bucketName;

    @Value("${bucket.type}")
    private String bucketType;

    @PostConstruct
    private void init() {
        this.imgSvc = new ImageService(
                new FileServiceFactory(bucketUrl, bucketUser, bucketKey, bucketName)
                        .getFileService(bucketType)
        );
    }

    @Autowired
    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    // ======================== SERVICE RPC STARTS HERE ========================
    @Override
    public void registerUser(CreateUser request, StreamObserver<User> responseObserver) {
        try {
            String imagePath = "";
            String imageThumbPath = "";
            if(!request.getImageContent().isEmpty()) {
                imagePath = imgSvc.saveImage(request.getImageContent(), request.getImageName(), "users/img/");
                imageThumbPath = imgSvc.uploadImageThumb(request.getImageContent(), request.getImageName(), "users/img/thumb/");
            }
            final UserEntity userEntity = userMapper.toEntity(request);
            userEntity.setImage(imagePath);
            userEntity.setImageThumb(imageThumbPath);
            userRepo.save(userEntity);

            User user = userMapper.toGRPC(userEntity);
            responseObserver.onNext(user);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Unexpected error").asRuntimeException());
        }
    }

    @Override
    public void updateCurrentUser(UpdateUser request, StreamObserver<User> responseObserver) {
        try {
            String imagePath = "";
            String imageThumbPath = "";
            if(!request.getUser().getImageContent().isEmpty()) {
                imagePath = imgSvc.saveImage(request.getUser().getImageContent(), request.getUser().getImageName(), "users/img/");
                imageThumbPath = imgSvc.uploadImageThumb(request.getUser().getImageContent(), request.getUser().getImageName(), "users/img/thumb/");
            }
            final UserEntity userEntity = userMapper.toEntity(request.getUser());
            userEntity.setImage(imagePath);
            userEntity.setImageThumb(imageThumbPath);
            userRepo.save(userEntity);

            User user = userMapper.toGRPC(userEntity);
            responseObserver.onNext(user);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Unexpected error").asRuntimeException());
        }
    }

    @Override
    public void currentUser(Int64Value request, StreamObserver<User> responseObserver) {
        try {
            final User user = userMapper.toGRPC(userRepo.findById((long) request.getValue()).orElseThrow(() -> new GrpcResourceNotFoundException("User not found")));
            responseObserver.onNext(user);
            responseObserver.onCompleted();
        } catch (GrpcResourceNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Unexpected error").asRuntimeException());
        }
    }

    @Override
    public void activateUser(Int64Value request, StreamObserver<User> responseObserver) {
        try {
            final UserEntity user = userRepo.findById((long) request.getValue()).orElseThrow(() -> new GrpcResourceNotFoundException("User not found"));
            if(user.getStatus().equals(UserStatus.REGISTERED)) {
                user.setStatus(UserStatus.ACTIVATED);
                userRepo.save(user);
            }

            responseObserver.onNext(userMapper.toGRPC(user));
            responseObserver.onCompleted();
        } catch (GrpcResourceNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Unexpected error").asRuntimeException());
        }
    }

    @Override
    public void deactivateUser(Int64Value request, StreamObserver<User> responseObserver) {
        try {
            final UserEntity user = userRepo.findById((long) request.getValue()).orElseThrow(() -> new GrpcResourceNotFoundException("User not found"));
            user.setStatus(UserStatus.INACTIVE);
            userRepo.save(user);

            responseObserver.onNext(userMapper.toGRPC(user));
            responseObserver.onCompleted();
        } catch (GrpcResourceNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Unexpected error").asRuntimeException());
        }
    }
}
