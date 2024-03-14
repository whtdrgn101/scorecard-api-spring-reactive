package com.tdtech.scorecardapi.controllertests;

import com.tdtech.scorecardapi.user.controllers.UserController;
import com.tdtech.scorecardapi.user.entities.UserDto;
import com.tdtech.scorecardapi.user.entities.UserRequest;
import com.tdtech.scorecardapi.user.entities.UserResponse;
import com.tdtech.scorecardapi.user.repositories.UserRepository;
import com.tdtech.scorecardapi.user.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;


public class UserControllerTests {

    @Test
    void shouldReturnListOfUsers() {
        WebTestClient client;
        UserRepository mockRepository = Mockito.mock(UserRepository.class);
        Mockito.when(mockRepository.findAll()).thenReturn(Flux.empty());
        UserService userService = new UserService(mockRepository);
        UserController controller = new UserController(userService);

        client = WebTestClient.bindToController(controller).build();

        client.get().uri(uriBuilder -> uriBuilder.path("/scorecard-api/v1.0/users").build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("[]");

    }

    @Test
    void shouldReturnSingleUser() {
        WebTestClient client;
        var userId = "abc123";
        UserRepository mockRepository = Mockito.mock(UserRepository.class);
        Mockito.when(mockRepository.findById(userId)).thenReturn(Mono.empty());
        UserService userService = new UserService(mockRepository);
        UserController controller = new UserController(userService);

        client = WebTestClient.bindToController(controller).build();

        client.get().uri(uriBuilder -> uriBuilder.path("/scorecard-api/v1.0/users/" + userId).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }

    @Test
    void shouldReturnCreatedAfterUserCreate() {
        WebTestClient client;
        var userRequest = new UserRequest("testFirst", "testLast", "test@email.com", "testPass", null);
        UserDto userDto = new UserDto(userRequest);
        userDto.setId("id1");
        UserResponse userResponse = new UserResponse(userDto);


        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userService.createUser(userRequest)).thenReturn(Mono.just(userResponse));
        UserController controller = new UserController(userService);

        client = WebTestClient.bindToController(controller).build();

        client.post().uri(uriBuilder -> uriBuilder.path("/scorecard-api/v1.0/users").build())
                .bodyValue(userRequest)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void shouldReturnOkAfterUserUpdate() {
        WebTestClient client;
        var userRequest = new UserRequest("testFirst", "testLast", "test@email.com", "testPass", null);
        UserDto userDto = new UserDto(userRequest);
        userDto.setId("id1");
        UserResponse userResponse = new UserResponse(userDto);


        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userService.updateUserById(userDto.getId(), userRequest)).thenReturn(Mono.just(userResponse));
        UserController controller = new UserController(userService);

        client = WebTestClient.bindToController(controller).build();

        client.put().uri(uriBuilder -> uriBuilder.path("/scorecard-api/v1.0/users/" + userDto.getId()).build())
                .bodyValue(userRequest)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
}
