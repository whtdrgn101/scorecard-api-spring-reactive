package com.tdtech.scorecardapi.user.controllers;

import com.tdtech.scorecardapi.user.entities.UserRequest;
import com.tdtech.scorecardapi.user.entities.UserResponse;
import com.tdtech.scorecardapi.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/scorecard-api/v1.0/users")
public class UserController {

    final UserService userService;

    public UserController(UserService us) {
        this.userService = us;
    }

    @PostMapping
    public Mono<ResponseEntity<UserResponse>> createUser(@RequestBody UserRequest user) {
        Mono<UserResponse> response = userService.createUser(user);
        return response.map(u -> new ResponseEntity<>(u, HttpStatus.CREATED));
    }

    @GetMapping
    public Flux<UserResponse> readAllUsers() {
        return userService.readAllUsers();
    }

    @GetMapping("{id}")
    public Mono<UserResponse> readUserById(@PathVariable String id) {
        return userService.readUserById(id);
    }

    @PutMapping("{id}")
    public Mono<UserResponse> updateUserById(@PathVariable String id, @RequestBody UserRequest user) {
        return userService.updateUserById(id, user);
    }
}
