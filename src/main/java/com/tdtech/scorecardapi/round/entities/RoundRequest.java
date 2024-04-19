package com.tdtech.scorecardapi.round.entities;

import com.tdtech.scorecardapi.bow.entities.BowRequest;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoundRequest {
    private String userId;
    private BowRequest bow;
    private String roundType;
    private Date roundDate;
    private String location;
    private String notes;
    private List<EndRequest> ends;
    private int score;
}
