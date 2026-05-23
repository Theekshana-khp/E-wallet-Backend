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

    public Boolean addNewUser(UserDto userDetails , String keycloakId){
        userEntity user = new userEntity();

        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPhone(userDetails.getPhone());
        user.setNic(userDetails.getNic());
        user.setKeycloakId(keycloakId);
        user.setDateOfBirth(userDetails.getDateOfBirth());
        user.setAddress(userDetails.getAddress());

        return userRepo.save(user).getId() != null;
    }

    public Long getUserIdByKeycloakId(String keycloakId){
        userEntity entity = userRepo.findByKeycloakId(keycloakId)
                .orElse(new userEntity());
        return entity.getId();
    }

    public UserDto getUserByKeycloakId(String keycloakId){

        UserDto returnDetails = new UserDto();

        userEntity entity = userRepo.findByKeycloakId(keycloakId)
                .orElse(new userEntity());

        returnDetails.setEmail(entity.getEmail());
        returnDetails.setFirstName(entity.getFirstName());
        returnDetails.setLastName(entity.getLastName());
        returnDetails.setPhone(entity.getPhone());
        returnDetails.setNic(entity.getNic());
        returnDetails.setDateOfBirth(entity.getDateOfBirth());
        returnDetails.setAddress(entity.getAddress());

        return returnDetails;
    }

}
