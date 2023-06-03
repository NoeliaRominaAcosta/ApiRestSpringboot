package com.itrsa.monolith.service;

import com.itrsa.monolith.mapper.UserMapper;
import com.itrsa.monolith.dto.UserDTO;
import com.itrsa.monolith.entity.User;
import com.itrsa.monolith.exception.RandomException;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;
import java.util.Random;

@Component
public class UserServiceBusiness {

	private static Random randomNum = new Random();
    private UserService userService;

    public UserServiceBusiness(UserService userService) {
        this.userService = userService;
    }

    public UserDTO getUser() throws IOException {
        var response = userService.getUser("eugenp").execute();
        var mapper = Mappers.getMapper(UserMapper.class);
        return mapper.userToUserDTO(response.body());
    }

    public Response<User> getUserRandomException() throws RandomException, IOException {
    	
        int showMe = randomNum.nextInt(100);

        if (showMe % 5 == 0) {
            throw new RandomException("Error de Prueba");
        }

        return userService.getUser("eugenp").execute();
    }
}
