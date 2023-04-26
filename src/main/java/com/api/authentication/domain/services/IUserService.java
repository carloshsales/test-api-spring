package com.api.authentication.domain.services;


import com.api.authentication.domain.entities.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> save(User user) throws Exception;
    Optional<User> edit(UUID id, User user) throws Exception;
    void delete(User user) throws Exception;

}
