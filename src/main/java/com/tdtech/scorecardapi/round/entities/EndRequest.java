package com.tdtech.scorecardapi.round.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EndRequest {
    private int sequence;
    private int score;
}
