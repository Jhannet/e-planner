package com.diplomado.eplanner.services;

import com.diplomado.eplanner.dto.RolDTO;
import com.diplomado.eplanner.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface RolService {
    List<RolDTO> listRoles();
    RolDTO save(RolDTO dto);
    Optional<RolDTO> getRolById(Integer rolId);
    void delete(Integer rolId);
}
