package com.BitzNomad.identity_service.RestController;

import com.BitzNomad.identity_service.DtoReponese.ApiResponse;
import com.BitzNomad.identity_service.DtoReponese.UserReponese;
import com.BitzNomad.identity_service.DtoRequest.UserCreateRequest;
import com.BitzNomad.identity_service.DtoRequest.UserUpdateRequest;
import com.BitzNomad.identity_service.Mapper.UserMapper;
import com.BitzNomad.identity_service.Service.UserService;
import com.BitzNomad.identity_service.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.internal.lang.reflect.StringToType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);
    @Autowired
    UserService userService;

    @PostMapping
    public ApiResponse<UserReponese> createUser( @RequestBody @Valid UserCreateRequest request) {
        log.info("User created");
        return ApiResponse.<UserReponese>builder()
                .status(HttpStatus.CREATED.value())
                .message("User created")
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping("/myinfo")
    public ApiResponse<UserReponese> getMyInfo() {
        return ApiResponse.<UserReponese>builder()
                .message("Your account login is info :")
                .status(200)
                .result(userService.getMyInfo())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserReponese>> getUserById(@PathVariable String id) {
        log.info("Getting user with id: {}", id);
        ApiResponse<UserReponese> result = new ApiResponse<>();
        result.setResult(UserMapper.convertUserToReponese(userService.getUserById(id)));
        result.setStatus(200);
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ApiResponse<UserReponese> updateUser(@RequestBody @Valid  UserUpdateRequest request) {
        log.info("Updating user with id: {}" , request.getUsername());
        return ApiResponse.<UserReponese>builder()
                .message("User"+ request.getUsername()+ "updated successfully")
                .status(200)
                .result(UserMapper.convertUserToReponese(userService.updateUser(request)))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        log.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
    }

    @GetMapping
    public ApiResponse<List<UserReponese>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("User name: {}", authentication.getName());
        authentication.getAuthorities().forEach(g -> log.info("GrantedAuthority: {}", g));
        return ApiResponse.<List<UserReponese>>builder()
                .status(200)
                .message("All users")
                .result(userService.getAllUsers())
                .build();
    }


}
