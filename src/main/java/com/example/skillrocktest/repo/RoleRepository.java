package com.example.skillrocktest.repo;

import com.example.skillrocktest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
