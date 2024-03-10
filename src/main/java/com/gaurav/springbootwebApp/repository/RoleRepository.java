package com.gaurav.springbootwebApp.repository;

import com.gaurav.springbootwebApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
