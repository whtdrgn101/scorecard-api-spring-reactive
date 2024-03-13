package com.tdtech.scorecardapi.round.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndRequest {
    private int sequence;
    private int score;
}
