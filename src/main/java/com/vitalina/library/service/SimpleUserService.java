package com.vitalina.library.service;

import com.vitalina.library.domain.Authority;
import com.vitalina.library.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vitalina.library.repository.UserRepository;
import java.util.ArrayList;


@Service
public class SimpleUserService implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.getUserByUserName(username);
    }

    @Override
    @Transactional
    public Long save(User user) {
        user.setEnabled(true);
        userRepository.save(user);
        ArrayList<Authority> list = new ArrayList<Authority>();
        Authority authority = new Authority("ROLE_USER");
        authority.setUser(user);
        list.add(authority);
        user.setAuthority(list);
        return user.getId();
    }
}