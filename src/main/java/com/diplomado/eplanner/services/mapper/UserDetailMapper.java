package com.diplomado.eplanner.services.mapper;

import com.diplomado.eplanner.domain.entities.UserDetail;
import com.diplomado.eplanner.dto.UserDetailDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDetailMapper implements CustomMapper<UserDetailDTO, UserDetail>{
    @Override
    public UserDetailDTO toDto(UserDetail userDetail) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setId(userDetail.getId());
        userDetailDTO.setFirstName(userDetail.getFirstName());
        userDetailDTO.setLastName(userDetail.getLastName());
        userDetailDTO.setAge(userDetail.getAge());
        userDetailDTO.setBirthDay(userDetail.getBirthDay());

        return userDetailDTO;
    }

    @Override
    public UserDetail toEntity(UserDetailDTO userDetailDTO) {
        UserDetail userDetail = new UserDetail();
        userDetail.setId(userDetailDTO.getId());
        userDetail.setFirstName(userDetailDTO.getFirstName());
        userDetail.setLastName(userDetailDTO.getLastName());
        userDetail.setAge(userDetailDTO.getAge());
        userDetail.setBirthDay(userDetailDTO.getBirthDay());

        return userDetail;
    }
}
