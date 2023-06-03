package com.itrsa.monolith;

import com.itrsa.monolith.dto.UserDTO;
import com.itrsa.monolith.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserUnitTest {

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    @Test
    public void givenEmployeeDTOwithDiffNametoEmployee_whenMaps_thenCorrect() {
        var dto = new UserDTO();
        dto.setUser("User");
        dto.setBiography("biography");

        var entity = mapper.userDTOtoUser(dto);

        assertEquals(dto.getUser(), entity.getLogin());
        assertEquals(dto.getBiography(), entity.getBio());
    }
}
