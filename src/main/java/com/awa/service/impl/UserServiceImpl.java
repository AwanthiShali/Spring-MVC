package com.awa.service.impl;

import com.awa.dto.UserDTO;
import com.awa.entity.User;
import com.awa.exception.ValidateException;
import com.awa.repo.UserRepo;
import com.awa.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDTO validateUser(String email, String password) {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        System.out.println(optionalUser + " find method");
        if (optionalUser.isPresent()) {
            return mapper.map(optionalUser.get(), UserDTO.class);
        } else {
            throw new ValidateException("There is no User for this email");
        }
    }
}
