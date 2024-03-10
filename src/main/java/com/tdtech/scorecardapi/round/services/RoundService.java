package com.tdtech.scorecardapi.round.services;

import com.tdtech.scorecardapi.round.entities.RoundDto;
import com.tdtech.scorecardapi.round.entities.RoundRequest;
import com.tdtech.scorecardapi.round.entities.RoundResponse;
import com.tdtech.scorecardapi.round.repositories.RoundRepository;
import com.tdtech.scorecardapi.user.entities.UserDto;
import com.tdtech.scorecardapi.user.entities.UserResponse;
import com.tdtech.scorecardapi.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class RoundService {

    final RoundRepository roundRepository;
    final UserRepository userRepository;


    public Flux<RoundResponse> readRoundListByUser(String userId, int pageNo, int limit) {
        Pageable pageable = PageRequest.of(pageNo -1, limit);
        return roundRepository.findByUserId(userId, pageable).log()
                .map(RoundResponse::new).switchIfEmpty(Flux.empty());
    }

    public Mono<RoundResponse> readRoundByUserId(String userId, String roundId) {
        return roundRepository.findByIdAndUserId(roundId, userId).log()
                .map(RoundResponse::new)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<RoundResponse> createRound(RoundRequest round, String userId) {
        Mono<UserDto> userMono = userRepository.findById(userId);
        return userMono.flatMap((foundUser) -> {
            RoundDto newRound = new RoundDto(round, foundUser);
            return roundRepository.save(newRound);
        }).map(RoundResponse::new);
    }
}
