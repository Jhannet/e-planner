package com.diplomado.eplanner.services.mapper;

import com.diplomado.eplanner.domain.entities.User;
import com.diplomado.eplanner.domain.entities.UserDetail;
import com.diplomado.eplanner.dto.UserCreationDTO;
import com.diplomado.eplanner.dto.UserDTO;
import com.diplomado.eplanner.dto.UserDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements CustomMapper<UserDTO, User> {
    private UserDetailMapper userDetailMapper;

    @Autowired
    public void setUserDetailMapper(UserDetailMapper userDetailMapper) {
        this.userDetailMapper = userDetailMapper;
    }
    @Override
    public UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }

    public UserDTO toDtoDetailed(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());

        if (user.getUserDetail() != null) {
            UserDetail userDetail = user.getUserDetail();
            dto.setUserDetail(userDetailMapper.toDto(userDetail));
        }

        return dto;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setCreatedAt(dto.getCreatedAt());

        return user;
    }

    public User toEntity(UserCreationDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setCreatedAt(dto.getCreatedAt());
        user.setActive(Boolean.TRUE);

        return user;
    }
}
