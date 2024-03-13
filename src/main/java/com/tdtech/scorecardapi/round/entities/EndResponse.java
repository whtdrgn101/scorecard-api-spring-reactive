package com.tdtech.scorecardapi.round.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndResponse {
    private int sequence;
    private int score;

    public EndResponse(EndDto end) {
        this.sequence = end.getSequence();
        this.score = end.getScore();
    }
}
