package com.diplomado.eplanner.repositories;

import com.diplomado.eplanner.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
