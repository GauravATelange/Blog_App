package com.gaurav.springbootwebApp.repository;

import com.gaurav.springbootwebApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
