package com.diplomado.eplanner.repositories;

import com.diplomado.eplanner.domain.entities.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RolRepository extends CrudRepository<Rol, Integer> {
    List<Rol> findAll();
}
