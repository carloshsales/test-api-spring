package com.api.authentication.controllers;

import com.api.authentication.domain.entities.user.User;
import com.api.authentication.domain.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service){
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll(){
        List<User> users = this.service.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id){
        Optional<User> userFound = this.service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userFound.get());
    }

    @PostMapping("/")
    public ResponseEntity<User> save(@RequestBody User user) throws Exception {
        Optional<User> registeredUser = this.service.save(user);
        /*
        String uri = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost:8080")
                .path("/users/"+registeredUser.get().getId())
                .build()
                .toString();
         */
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(registeredUser.get().getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> edit(@PathVariable UUID id, @RequestBody User user) throws Exception {
        this.service.edit(id, user);
        return ResponseEntity.noContent().build();
    }
}
