package com.network.network.repository;

import com.network.network.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository <User, UUID>
{
    User findByUsername(String username);
}
