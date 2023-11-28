package com.diplomado.eplanner.repositories;

import com.diplomado.eplanner.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByActiveOrderByUsernameAsc(Boolean active);
}
