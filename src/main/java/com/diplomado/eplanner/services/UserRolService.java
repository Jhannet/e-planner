package com.diplomado.eplanner.services;

import com.diplomado.eplanner.dto.UserDTO;
import com.diplomado.eplanner.dto.UserRolDTO;

import java.util.List;
import java.util.Optional;

public interface UserRolService {
    UserRolDTO save(UserRolDTO dto);
    Optional<UserRolDTO> getUserRolById(Integer userRolId);
    void logicalDelete(Integer userRolId);
    void logicalDelete(Long userId, Integer rolId);
    List<UserDTO> listUsersByRol (Integer rolId);
}
