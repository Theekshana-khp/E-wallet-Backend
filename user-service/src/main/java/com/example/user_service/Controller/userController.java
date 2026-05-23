package com.example.user_service.Controller;

import com.example.user_service.Dto.UserDto;
import com.example.user_service.Service.userService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/user")
public class userController {
    private final userService userServices;

    public userController(userService userService){
        this.userServices = userService;
    }

    @GetMapping("/isExist")
    public Boolean isUserExist(@AuthenticationPrincipal Jwt jwt){
        String keycloakId = jwt.getSubject();
        return userServices.isUserAdded(keycloakId);
    }

    @GetMapping("/getuser")
    public UserDto getUser(@RequestParam String keycloakId){
        if (keycloakId == null || keycloakId.isEmpty()){
            return null;
        }

        return userServices.getUserByKeycloakId(keycloakId);
    }

    @GetMapping("/getId")
    public Long getUserId(@RequestParam String keycloakId){
        if (keycloakId == null || keycloakId.isEmpty()){
            return null;
        }

        return userServices.getUserIdByKeycloakId(keycloakId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserDto user , @AuthenticationPrincipal Jwt jwt){
        String keycloakId = jwt.getSubject();
        boolean created = userServices.addNewUser(user ,keycloakId);

        if (!created) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User not created");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User created successfully");
    }
}
