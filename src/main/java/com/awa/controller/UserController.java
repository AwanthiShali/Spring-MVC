package com.awa.controller;

import com.awa.dto.UserDTO;
import com.awa.exception.NotFoundException;
import com.awa.service.UserService;
import com.awa.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> SearchUser(@RequestHeader("email") String email, @RequestHeader("password") String password) {
        System.out.println(email + " username login ");
        System.out.println(password + " password login ");
        if (email.trim().length() <= 0) {
            throw new NotFoundException("userName cannot be empty");
        } else {
            String reversed = new StringBuilder(password).reverse().toString();
            byte[] bytes = reversed.getBytes();
            UserDTO userDTO = userService.validateUser(email, Base64.getEncoder().encodeToString(bytes));
            return new ResponseEntity<>(new StandardResponse("500", "User login success", userDTO), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
