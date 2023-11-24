package com.diplomado.eplanner.services.implement;

import com.diplomado.eplanner.dto.RolDTO;
import com.diplomado.eplanner.repositories.RolRepository;
import com.diplomado.eplanner.services.RolService;
import com.diplomado.eplanner.services.mapper.RolMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    public RolServiceImpl(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolDTO> listRoles() {
        return rolRepository.findAll()
                .stream()
                .map(rolMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RolDTO save(RolDTO dto) {
        return this.rolMapper
                .toDto(rolRepository.save(this.rolMapper.toEntity(dto)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RolDTO> getRolById(Integer rolId) {
        return rolRepository.findById(rolId).map(rolMapper::toDto);
    }

    @Override
    public void delete(Integer rolId) {
        rolRepository.deleteById(rolId);
    }
}
