package com.tdtech.scorecardapi.round.services;

import com.tdtech.scorecardapi.bow.entities.BowDto;
import com.tdtech.scorecardapi.exceptions.ResourceNotFoundException;
import com.tdtech.scorecardapi.round.entities.RoundDto;
import com.tdtech.scorecardapi.round.entities.RoundRequest;
import com.tdtech.scorecardapi.round.entities.RoundResponse;
import com.tdtech.scorecardapi.round.repositories.RoundRepository;
import com.tdtech.scorecardapi.user.entities.UserDto;
import com.tdtech.scorecardapi.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@EnableAutoConfiguration
public class RoundService {

    final RoundRepository roundRepository;
    final UserRepository userRepository;

    public Flux<RoundResponse> readRoundListByUser(String userId, int pageNo, int limit) {
        Pageable pageable = PageRequest.of(pageNo -1, limit);
        return roundRepository.findByUserId(userId, pageable).log()
                .map(RoundResponse::new).switchIfEmpty(Flux.error(new ResourceNotFoundException(userId, "User")));
    }

    public Mono<RoundResponse> readRoundByUserId(String userId, String roundId) {
        return roundRepository.findByIdAndUserId(roundId, userId).log()
                .map(RoundResponse::new)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(userId, "User")));
    }

    public Mono<RoundResponse> createRound(RoundRequest round, String userId) {
        Mono<UserDto> userMono = userRepository.findById(userId);
        return userMono.flatMap((foundUser) -> {
            RoundDto newRound = new RoundDto(round, foundUser);
            return roundRepository.save(newRound);
        }).map(RoundResponse::new);
    }

    public Mono<RoundResponse> updateRound(RoundRequest r, String userId, String roundId) {
        Mono<UserDto> userMono = userRepository.findById(userId).switchIfEmpty(Mono.error(new ResourceNotFoundException(userId, "User")));
        Mono<RoundDto> roundMono = roundRepository.findByIdAndUserId(roundId, userId).switchIfEmpty(Mono.error(new ResourceNotFoundException(roundId, "Round")));
        return Mono.zip(userMono, roundMono).log().flatMap(data -> {
            UserDto user = data.getT1();
            RoundDto round = data.getT2();
            round.setUser(user);
            round.setRoundDate(r.getRoundDate());
            if(r.getBow() != null)
                round.setBow(new BowDto(r.getBow()));
            else
                round.setBow(null);
            round.setLocation(r.getLocation());
            round.setNotes(r.getNotes());
            round.setScore(r.getScore());
            round.setRoundType(r.getRoundType());
            return roundRepository.save(round);
        }).map(RoundResponse::new);
    }
}
