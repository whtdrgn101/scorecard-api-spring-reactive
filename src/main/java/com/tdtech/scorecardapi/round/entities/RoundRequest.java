package com.tdtech.scorecardapi.round.entities;

import com.tdtech.scorecardapi.bow.entities.BowRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
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
