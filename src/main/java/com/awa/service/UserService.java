package com.awa.service;

import com.awa.dto.UserDTO;

public interface UserService {
    UserDTO validateUser(String email, String password);
}
