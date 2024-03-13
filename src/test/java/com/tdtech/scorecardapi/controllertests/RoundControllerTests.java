package com.tdtech.scorecardapi.controllertests;

import com.tdtech.scorecardapi.round.controllers.RoundController;
import com.tdtech.scorecardapi.round.repositories.RoundRepository;
import com.tdtech.scorecardapi.round.services.RoundService;
import com.tdtech.scorecardapi.user.controllers.UserController;
import com.tdtech.scorecardapi.user.repositories.UserRepository;
import com.tdtech.scorecardapi.user.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
}
