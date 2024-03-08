package com.tdtech.scorecardapi.round.entities;

import com.tdtech.scorecardapi.bow.entities.BowResponse;
import com.tdtech.scorecardapi.user.entities.UserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RoundResponse {
    private String id;
    private UserResponse user;
    private BowResponse bow;
    private String roundType;
    private Date roundDate;
    private String location;
    private String notes;
    private int score;

    public RoundResponse(RoundDto round) {
        this.id = round.getId();
        if(round.getUser() != null) {
            this.user = new UserResponse(round.getUser());
        }
        if(round.getBow() != null) {
            this.bow = new BowResponse(round.getBow());
        }
        this.roundType = round.getRoundType();
        this.roundDate = round.getRoundDate();
        this.location = round.getLocation();
        this.notes = round.getNotes();
        this.score = round.getScore();
    }
}
