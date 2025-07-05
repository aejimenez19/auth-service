package com.aejimenez.dev.auth_service.repositories;

import com.aejimenez.dev.auth_service.entities.Rol;
import com.aejimenez.dev.auth_service.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface RolRepository extends JpaRepository<Rol, UUID> {


    Optional<Rol> findByRol(Roles roles);
}
