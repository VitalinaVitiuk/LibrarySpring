package com.vitalina.library.service;

import com.vitalina.library.domain.User;

public interface UserService {
    User getUserById(Long id);
    User getUserByUserName (String username);
    Long save(User user);
}
