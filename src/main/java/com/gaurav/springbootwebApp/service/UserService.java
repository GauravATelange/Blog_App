package com.gaurav.springbootwebApp.service;

import com.gaurav.springbootwebApp.DTO.RegistrationDto;
import com.gaurav.springbootwebApp.entity.User;

public interface UserService {


    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}


