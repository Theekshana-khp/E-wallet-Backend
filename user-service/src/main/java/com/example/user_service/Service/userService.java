package com.example.user_service.Service;

import com.example.user_service.Dto.UserDto;
import com.example.user_service.Entity.userEntity;
import com.example.user_service.Repositery.userRepositery;
import org.springframework.stereotype.Service;

@Service
public class userService {
    private userRepositery userRepo;

    public userService(userRepositery userRepo){
        this.userRepo = userRepo;
    }

    public Boolean isUserAdded(String keycloakId){
        return userRepo.existsByKeycloakId(keycloakId);
    }

    public Boolean addNewUser(UserDto userDetails){
        userEntity user = new userEntity();

        user.setEmail(userDetails.getEmail());
        return userRepo.save(user).getId() != null;
    }

}
