package com.tdtech.scorecardapi.round.controllers;

import com.tdtech.scorecardapi.round.entities.RoundResponse;
import com.tdtech.scorecardapi.round.services.RoundService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

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
}
