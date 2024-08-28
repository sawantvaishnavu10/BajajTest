package com.app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.entity.User;

@Service
public class UserService {
    private final Map<Long, User> usersByPhoneNumber = new HashMap<>();
    private final Map<String, User> usersByEmailId = new HashMap<>();

    public boolean createUser(User user) {
        if (usersByPhoneNumber.containsKey(user.getPhoneNumber()) ||
            usersByEmailId.containsKey(user.getEmailId())) {
            return false; // Duplicate found
        }
        usersByPhoneNumber.put(user.getPhoneNumber(), user);
        usersByEmailId.put(user.getEmailId(), user);
        return true;
    }
}
