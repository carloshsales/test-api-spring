package com.api.authentication.services;

import com.api.authentication.data.repositories.IUserRepository;
import com.api.authentication.domain.entities.user.User;
import com.api.authentication.domain.services.IUserService;
import com.api.authentication.exceptions.EmailExistsException;
import com.api.authentication.exceptions.UserExistsException;
import com.api.authentication.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final IUserRepository repository;

    public UserService(IUserRepository repository){
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        try{
            return this.repository.findById(id);
        }catch (NoSuchElementException e){
            throw new UserNotFoundException("User not found! id: " + id);
        }



    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    @Override
    public Optional<User> save(User user) {
        if(this.repository.existsByEmail(user.getEmail())){
            throw new EmailExistsException("Email is already in use");
        }
        if(this.repository.existsByUsername(user.getUsername())){
            throw new UserExistsException("This user already exists!");
        }

        return Optional.of(this.repository.save(user));
    }

    @Override
    public Optional<User> edit(UUID id, User user) {
            try {
                Optional<User> userFound = this.findById(id);
                user = updateData(userFound, user);
                return Optional.of(this.repository.save(user));
            }catch (UserNotFoundException e){
                e.getStackTrace();
                return Optional.empty();
            }
    }

    @Override
    public void delete(User user) {
        if(this.findById(user.getId()).isPresent()){
            this.repository.delete(user);
        }
        throw new UserNotFoundException("User not found");
    }

    private User updateData(Optional<User> userFound, User user) {
        User userToUpdate = userFound.get();

        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());

        return userToUpdate;
    }

}
