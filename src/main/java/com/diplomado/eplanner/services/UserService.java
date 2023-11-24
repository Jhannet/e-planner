package com.diplomado.eplanner.services;

import com.diplomado.eplanner.dto.UserCreationDTO;
import com.diplomado.eplanner.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> listUsers();
    List<UserDTO> listUsersDetailed();
    UserDTO save(UserCreationDTO dto);
    UserDTO update(UserCreationDTO dto);
    Optional<UserDTO> getUserById(Long userId);
    void delete(Long userId);
}
