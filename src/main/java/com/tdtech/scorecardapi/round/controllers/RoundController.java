package com.tdtech.scorecardapi.round.controllers;

import com.tdtech.scorecardapi.round.entities.RoundRequest;
import com.tdtech.scorecardapi.round.entities.RoundResponse;
import com.tdtech.scorecardapi.round.services.RoundService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/scorecard-api/v1.0")
@AllArgsConstructor
public class RoundController {

    final RoundService roundService;

    @GetMapping("/users/{userId}/rounds")
    public Flux<RoundResponse> readAllRoundsByUserId(@PathVariable String userId,
                                                     @RequestParam(defaultValue = "1") int pageNo,
                                                     @RequestParam(defaultValue = "25") int pageSize){
        return roundService.readRoundListByUser(userId, pageNo, pageSize);
    }

    @GetMapping("/users/{userId}/rounds/{roundId}")
    public Mono<RoundResponse> readRoundByUserId(@PathVariable String userId, @PathVariable String roundId) {
        return roundService.readRoundByUserId(userId, roundId);
    }

    @PostMapping("/users/{userId}/rounds")
    public Mono<ResponseEntity<RoundResponse>> createRound(@PathVariable String userId, @RequestBody RoundRequest round) {
        Mono<RoundResponse> roundResponseMono = roundService.createRound(round, userId);
        return roundResponseMono.map(r -> new ResponseEntity<>(r, HttpStatus.CREATED));
    }

    @PutMapping("/users/{userId}/rounds/{roundId}")
    public Mono<RoundResponse> updateRoundByUserId(@PathVariable String userId, @PathVariable String roundId, @RequestBody RoundRequest round) {
        return roundService.updateRound(round, userId, roundId);
    }
}
