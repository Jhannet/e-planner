package com.diplomado.eplanner.repositories;

import com.diplomado.eplanner.domain.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}
