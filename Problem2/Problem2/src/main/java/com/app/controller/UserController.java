package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.entity.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/automation-campus/create/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestHeader("roll-number") String rollNumber, @RequestBody User user) {

        // Validate roll number
        if (rollNumber == null || rollNumber.isEmpty()) {
            return new ResponseEntity<>("Roll number is missing", HttpStatus.UNAUTHORIZED);
        }

        boolean success = userService.createUser(user);

        if (success) {
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Duplicate phone number or email", HttpStatus.BAD_REQUEST);
        }
    }
}
