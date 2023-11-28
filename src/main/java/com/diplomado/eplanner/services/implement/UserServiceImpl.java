package com.diplomado.eplanner.services.implement;

import com.diplomado.eplanner.domain.entities.User;
import com.diplomado.eplanner.domain.entities.UserDetail;
import com.diplomado.eplanner.dto.UserCreationDTO;
import com.diplomado.eplanner.dto.UserDTO;
import com.diplomado.eplanner.repositories.UserDetailRepository;
import com.diplomado.eplanner.repositories.UserRepository;
import com.diplomado.eplanner.services.UserService;
import com.diplomado.eplanner.services.mapper.UserDetailMapper;
import com.diplomado.eplanner.services.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private UserDetailRepository userDetailRepository;
    private final UserMapper userMapper;
    private UserDetailMapper userDetailMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserDetailRepository(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Autowired
    public void setUserDetailMapper(UserDetailMapper userDetailMapper) {
        this.userDetailMapper = userDetailMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> listUsers() {
        return userRepository.findAllByActiveOrderByUsernameAsc(Boolean.TRUE)
                .stream()
                .map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> listUsersDetailed() {
        return userRepository.findAllByActiveOrderByUsernameAsc(Boolean.TRUE)
                .stream()
                .map(userMapper::toDtoDetailed).collect(Collectors.toList());
    }

    @Override
    public UserDTO save(UserCreationDTO dto) {
        User user = userRepository.save(this.userMapper.toEntity(dto));

        if(dto.getUserDetail() != null) {
            UserDetail userDetail = userDetailMapper.toEntity(dto.getUserDetail());
            userDetail.setUser(user);
            user.setUserDetail(userDetailRepository.save(userDetail));
            return this.userMapper.toDtoDetailed(user);
        }

        return this.userMapper.toDto(user);
    }

    @Override
    public UserDTO update(UserCreationDTO dto) {
        Optional<User> oldUser = userRepository.findById(dto.getId());

        if (oldUser.isEmpty()) {
            throw new EntityNotFoundException();
        }
        dto.setCreatedAt(oldUser.get().getCreatedAt());
        User user = userRepository.save(this.userMapper.toEntity(dto));

        if (dto.getUserDetail() != null) {
            UserDetail userDetail = userDetailMapper.toEntity(dto.getUserDetail());
            userDetail.setUser(user);
            user.setUserDetail(userDetailRepository.save(userDetail));
            return this.userMapper.toDtoDetailed(user);
        }

        return this.userMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserById(Long userId) {
        return userRepository.findById(userId).map(userMapper::toDtoDetailed);
    }

    @Override
    public void delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id: " + userId + "not exist."));
        user.setActive(Boolean.FALSE);
        userRepository.save(user);
    }
}
