package com.tdtech.scorecardapi.round.services;

import com.tdtech.scorecardapi.round.entities.RoundResponse;
import com.tdtech.scorecardapi.round.repositories.RoundRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class RoundService {

    final RoundRepository roundRepository;

    public Flux<RoundResponse> readRoundListByUser(String userId, int pageNo, int limit) {
        Pageable pageable = PageRequest.of(pageNo -1, limit);
        return roundRepository.findByUserId(userId, pageable).log().map(RoundResponse::new);
    }
}
