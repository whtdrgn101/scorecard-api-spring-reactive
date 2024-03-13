package com.tdtech.scorecardapi.servicetests;

import com.tdtech.scorecardapi.user.entities.UserDto;
import com.tdtech.scorecardapi.user.entities.UserRequest;
import com.tdtech.scorecardapi.user.repositories.UserRepository;
import com.tdtech.scorecardapi.user.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class UserServiceTests {

    @Test
    void shouldReturnUserWithIdWhenCreate() {
        UserRequest userRequest = new UserRequest("testFirst", "testLast", "testEmail", "testPass", null);
        UserDto userDto = new UserDto(userRequest);
        userDto.setId("testId1");

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.save(Mockito.any(UserDto.class))).thenReturn(Mono.just(userDto));
        UserService userService = new UserService(userRepository);

        StepVerifier.create(userService.createUser(userRequest))
                .expectNextMatches(user -> user.getId().equals("testId1"))
                .expectComplete()
                .verify();

    }

    @Test
    void shouldReturnUserWithIdWhenUpdate() {
        UserRequest userRequest = new UserRequest("testFirst", "testLast", "testEmail", "testPass", null);
        UserDto userDto = new UserDto(userRequest);
        String userId = "testId1";
        userDto.setId(userId);

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.save(Mockito.any(UserDto.class))).thenReturn(Mono.just(userDto));
        Mockito.when(userRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(userDto));
        UserService userService = new UserService(userRepository);

        StepVerifier.create(userService.updateUserById(userId,userRequest))
                .expectNextMatches(user -> user.getFirstName().equals(userRequest.getFirstName()))
                .expectComplete()
                .verify();

    }
}
