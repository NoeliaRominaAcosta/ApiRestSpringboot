package com.itrsa.monolith.mapper;

import com.itrsa.monolith.dto.UserDTO;
import com.itrsa.monolith.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target="user", source="entity.login")
    @Mapping(target="biography", source="entity.bio")
    UserDTO userToUserDTO(User entity);

    @Mapping(target="login", source="dto.user")
    @Mapping(target="bio", source="dto.biography")
    @Mapping(target = "id", ignore = true)
    User userDTOtoUser(UserDTO dto);
}
