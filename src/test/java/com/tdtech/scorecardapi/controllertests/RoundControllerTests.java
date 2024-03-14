package com.tdtech.scorecardapi.controllertests;

import com.tdtech.scorecardapi.bow.entities.BowRequest;
import com.tdtech.scorecardapi.exceptions.ResourceNotFoundException;
import com.tdtech.scorecardapi.round.controllers.RoundController;
import com.tdtech.scorecardapi.round.entities.RoundDto;
import com.tdtech.scorecardapi.round.entities.RoundRequest;
import com.tdtech.scorecardapi.round.entities.RoundResponse;
import com.tdtech.scorecardapi.round.services.RoundService;
import com.tdtech.scorecardapi.user.entities.UserDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Date;

public class RoundControllerTests {

    @Test
    void shouldReturnOkForRoundListRetrieval() {
        WebTestClient client;
        RoundService roundService = Mockito.mock(RoundService.class);
        Mockito.when(roundService.readRoundListByUser(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(Flux.empty());
        RoundController controller = new RoundController(roundService);

        client = WebTestClient.bindToController(controller).build();

        client.get().uri(uriBuilder -> uriBuilder.path("/scorecard-api/v1.0/users/abc123/rounds")
                        .queryParam("pageNo","1")
                        .queryParam("pageSize","25")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("[]");
    }

    @Test
    void shouldReturnOkForRoundRetrieval() {
        WebTestClient client;
        RoundService roundService = Mockito.mock(RoundService.class);
        Mockito.when(roundService.readRoundByUserId(Mockito.anyString(), Mockito.anyString())).thenReturn(Mono.empty());
        RoundController controller = new RoundController(roundService);

        client = WebTestClient.bindToController(controller).build();

        client.get().uri(uriBuilder -> uriBuilder.path("/scorecard-api/v1.0/users/abc123/rounds/abc123").build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void shouldReturnCreatedAfterRoundCreate() {
        WebTestClient client;
        BowRequest bowRequest = Mockito.mock(BowRequest.class);
        UserDto userDto = Mockito.mock(UserDto.class);
        userDto.setId("userId1");
        RoundRequest roundRequest = new RoundRequest(userDto.getId(), bowRequest,"NFAA",new Date(),"locationTest1","notesTest1", null, 50);
        RoundDto roundDto = new RoundDto(roundRequest,userDto);
        RoundResponse roundResponse = new RoundResponse(roundDto);

        RoundService roundService = Mockito.mock(RoundService.class);
        Mockito.when(roundService.createRound(Mockito.any(RoundRequest.class), Mockito.anyString())).thenReturn(Mono.just(roundResponse));
        RoundController controller = new RoundController(roundService);

        client = WebTestClient.bindToController(controller).build();

        client.post().uri(uriBuilder -> uriBuilder.path("/scorecard-api/v1.0/users/" + userDto.getId() + "/rounds").build())
                .bodyValue(roundRequest)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void shouldReturnOkAfterRoundUpdate() {
        WebTestClient client;
        BowRequest bowRequest = Mockito.mock(BowRequest.class);
        UserDto userDto = Mockito.mock(UserDto.class);
        userDto.setId("userId1");
        RoundRequest roundRequest = new RoundRequest(userDto.getId(), bowRequest,"NFAA",new Date(),"locationTest1","notesTest1", null, 50);
        RoundDto roundDto = new RoundDto(roundRequest,userDto);
        roundDto.setId("roundId1");
        RoundResponse roundResponse = new RoundResponse(roundDto);

        RoundService roundService = Mockito.mock(RoundService.class);
        Mockito.when(roundService.updateRound(Mockito.any(RoundRequest.class), Mockito.anyString(), Mockito.anyString())).thenReturn(Mono.just(roundResponse));
        RoundController controller = new RoundController(roundService);

        client = WebTestClient.bindToController(controller).build();

        client.put().uri(uriBuilder -> uriBuilder.path("/scorecard-api/v1.0/users/" + userDto.getId() + "/rounds/" + roundDto.getId()).build())
                .bodyValue(roundRequest)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void shouldReturnNotFoundOnCreateForBadUser() {
        WebTestClient client;
        BowRequest bowRequest = Mockito.mock(BowRequest.class);
        UserDto userDto = Mockito.mock(UserDto.class);
        userDto.setId("userId1");
        RoundRequest roundRequest = new RoundRequest(userDto.getId(), bowRequest,"NFAA",new Date(),"locationTest1","notesTest1", null, 50);

        RoundService roundService = Mockito.mock(RoundService.class);
        Mockito.when(roundService.createRound(Mockito.any(RoundRequest.class), Mockito.anyString())).thenThrow(new ResourceNotFoundException("test", "User"));
        RoundController controller = new RoundController(roundService);

        client = WebTestClient.bindToController(controller).build();

        client.post().uri(uriBuilder -> uriBuilder.path("/scorecard-api/v1.0/users/" + userDto.getId() + "/rounds").build())
                .bodyValue(roundRequest)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }
}
