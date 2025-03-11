package com.portal.repository;

import com.portal.entity.userentity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface  RepositoryUser extends JpaRepository<userentity, Long> {
    Optional<userentity> findByEmail(String email);
}

