package com.tdtech.scorecardapi.user.services;

import com.tdtech.scorecardapi.user.entities.UserDto;
import com.tdtech.scorecardapi.user.entities.UserRequest;
import com.tdtech.scorecardapi.user.entities.UserResponse;
import com.tdtech.scorecardapi.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@EnableAutoConfiguration
public class UserService {

    final UserRepository userRepository;

    public Mono<UserResponse> createUser(UserRequest user) {
        UserDto newUser = new UserDto(user);
        Mono<UserDto> userMono = userRepository.save(newUser);
        return userMono.map(UserResponse::new);
    }
    public Flux<UserResponse> readAllUsers() {
        return userRepository.findAll().log().map(UserResponse::new);
    }

    public Mono<UserResponse> readUserById(String id) {
        return userRepository.findById(id).map(UserResponse::new);
    }

    public Mono<UserResponse> updateUserById(String id, UserRequest user) {
        Mono<UserDto> u = userRepository.findById(id);
        return u.flatMap((found) -> {
            found.setFirstName(user.getFirstName());
            found.setLastName(user.getLastName());
            found.setEmail(user.getEmail());
            found.fillBowsFromRequest(user.getBows());
            return userRepository.save(found);
        }).map(UserResponse::new);
    }
}
