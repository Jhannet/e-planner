package com.diplomado.eplanner.services.mapper;

import com.diplomado.eplanner.domain.entities.UserRol;
import com.diplomado.eplanner.dto.UserRolDTO;
import org.springframework.stereotype.Component;

@Component
public class UserRolMapper implements CustomMapper<UserRolDTO, UserRol>{
    private final UserMapper userMapper;
    private final RolMapper rolMapper;

    public UserRolMapper(UserMapper userMapper, RolMapper rolMapper) {
        this.userMapper = userMapper;
        this.rolMapper = rolMapper;
    }

    @Override
    public UserRolDTO toDto(UserRol userRol) {
        UserRolDTO userRolDTO = new UserRolDTO();
        userRolDTO.setId(userRol.getId());
        userRolDTO.setActive(userRol.getActive());
        userRolDTO.setCreatedAt(userRol.getCreatedAt());
        userRolDTO.setUser(userMapper.toDto(userRol.getUser()));
        userRolDTO.setRol(rolMapper.toDto(userRol.getRol()));

        return userRolDTO;
    }

    @Override
    public UserRol toEntity(UserRolDTO userRolDTO) {
        UserRol userRol = new UserRol();
        userRol.setId(userRolDTO.getId());
        userRol.setActive(userRolDTO.getActive());
        userRol.setCreatedAt(userRolDTO.getCreatedAt());
        userRol.setUser(userMapper.toEntity(userRolDTO.getUser()));
        userRol.setRol(rolMapper.toEntity(userRolDTO.getRol()));

        return userRol;
    }
}
