package com.jwtloadimage.jwtandloadimage.repositories;

import com.jwtloadimage.jwtandloadimage.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
