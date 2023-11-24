package com.diplomado.eplanner.services.implement;

import com.diplomado.eplanner.domain.entities.Rol;
import com.diplomado.eplanner.domain.entities.User;
import com.diplomado.eplanner.domain.entities.UserRol;
import com.diplomado.eplanner.dto.UserDTO;
import com.diplomado.eplanner.dto.UserRolDTO;
import com.diplomado.eplanner.repositories.UserRolRepository;
import com.diplomado.eplanner.services.UserRolService;
import com.diplomado.eplanner.services.mapper.UserMapper;
import com.diplomado.eplanner.services.mapper.UserRolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRolServiceImpl implements UserRolService {
    private final UserRolRepository userRolRepository;
    private final UserRolMapper userRolMapper;
    private UserMapper userMapper;

    public UserRolServiceImpl(UserRolRepository userRolRepository, UserRolMapper userRolMapper) {
        this.userRolRepository = userRolRepository;
        this.userRolMapper = userRolMapper;
    }

    @Override
    public UserRolDTO save(UserRolDTO dto) {
        return userRolMapper.toDto(userRolRepository.save(userRolMapper.toEntity(dto)));
    }

    @Override
    public Optional<UserRolDTO> getUserRolById(Integer userRolId) {
        return Optional.empty();
    }

    @Override
    public void logicalDelete(Integer userRolId) {
        Optional<UserRol> userRol = userRolRepository.findById(userRolId);
        if (userRol.isEmpty()) {
            throw new IllegalArgumentException("Rol for this user does not exist");
        }

        UserRol userRolDB = userRol.get();
        userRolDB.setActive(false);
        userRolRepository.save(userRolDB);
    }

    @Override
    public void logicalDelete(Long userId, Integer rolId) {
        UserRol userRol = userRolRepository.findAllByUserAndRol(new User(userId), new Rol(rolId)).getFirst();
        if (userRol == null) {
            throw new IllegalArgumentException("Rol for this user does not exist");
        }

        userRol.setActive(false);
        userRolRepository.save(userRol);
    }

    @Override
    public List<UserDTO> listUsersByRol (Integer rolId) {
        List<UserRol> userRolList = userRolRepository.findAllByRol(new Rol(rolId));
        return userRolList.stream().map(userRol -> userMapper.toDto(userRol.getUser())).toList();
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }
}
