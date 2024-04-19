package com.tdtech.scorecardapi.round.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EndDto {
    private int sequence;
    private int score;

    public EndDto(EndRequest end) {
        this.sequence = end.getSequence();
        this.score = end.getScore();
    }
}
