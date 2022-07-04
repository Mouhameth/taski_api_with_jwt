package com.jwtloadimage.jwtandloadimage.repositories;

import com.jwtloadimage.jwtandloadimage.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String roleName);
}
