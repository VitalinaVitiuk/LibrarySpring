package com.vitalina.library.repository;

import com.vitalina.library.domain.Issuance;
import com.vitalina.library.domain.User;

import java.util.List;

public interface UserRepository {

    User getUserById(Long id);
    User getUserByUserName (String username);
    List<Issuance> showAllIssuances(User user);
    Long save(User user);

}
