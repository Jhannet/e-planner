package com.diplomado.eplanner.repositories;

import com.diplomado.eplanner.domain.entities.Rol;
import com.diplomado.eplanner.domain.entities.User;
import com.diplomado.eplanner.domain.entities.UserRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRolRepository extends JpaRepository<UserRol, Integer> {
    List<UserRol> findAllByUserAndRol(User user, Rol rol);
    List<UserRol> findAllByRol(Rol rol);
}
