package com.mitsoa.banking.endpoint.rest.controller;

import com.mitsoa.banking.endpoint.rest.mapper.UserToCreateMapper;
import com.mitsoa.banking.endpoint.rest.model.UserRest;
import com.mitsoa.banking.endpoint.rest.model.UserToCreate;
import com.mitsoa.banking.endpoint.rest.model.UserToUpdate;
import com.mitsoa.banking.exception.UserNotFoundException;
import com.mitsoa.banking.model.User;
import com.mitsoa.banking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private UserToCreateMapper userToCreateMapper;

    @PostMapping("/users")
    public ResponseEntity<List<UserRest>> save(@RequestBody List<UserToCreate> usersToCreate) {
        List<User> users = usersToCreate.stream().map(userToCreateMapper).toList();
        List<User> usersCreated = userService.saveAll(users);
        List<UserRest> userRests = usersCreated.stream().map(userToCreateMapper::toRest).toList();
        return ResponseEntity.status(HttpStatus.OK).body(userRests);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(page,size));
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmail(email));
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<String> deleteByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteByEmail(email));
    }

    @PatchMapping("/users/{email}")
    public ResponseEntity<User> updateByEmail(@PathVariable String email, @RequestBody UserToUpdate userToUpdate) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateByEmail(email,userToUpdate));
    }
}
