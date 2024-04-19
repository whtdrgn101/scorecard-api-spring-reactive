package com.tdtech.scorecardapi.round.repositories;

import com.tdtech.scorecardapi.round.entities.RoundDto;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@EnableAutoConfiguration
public interface RoundRepository extends ReactiveMongoRepository<RoundDto, String> {

    public Flux<RoundDto> findByUserId(String userId, Pageable pageable);
    public Mono<RoundDto> findByIdAndUserId(String id, String userId);
}
