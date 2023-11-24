package com.diplomado.eplanner.services.mapper;

import com.diplomado.eplanner.domain.entities.Rol;
import com.diplomado.eplanner.dto.RolDTO;
import org.springframework.stereotype.Component;

@Component
public class RolMapper implements CustomMapper<RolDTO, Rol>{
    @Override
    public RolDTO toDto(Rol rol) {
        RolDTO dto = new RolDTO();
        dto.setId(rol.getId());
        dto.setName(rol.getName());

        return dto;
    }

    @Override
    public Rol toEntity(RolDTO dto) {
        Rol rol = new Rol();
        rol.setId(dto.getId());
        rol.setName(dto.getName());

        return rol;
    }
}
