package com.mitsoa.banking.endpoint.rest.controller;

import com.mitsoa.banking.endpoint.rest.mapper.UserToCreateMapper;
import com.mitsoa.banking.endpoint.rest.model.UserRest;
import com.mitsoa.banking.endpoint.rest.model.UserToCreate;
import com.mitsoa.banking.model.User;
import com.mitsoa.banking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
}
