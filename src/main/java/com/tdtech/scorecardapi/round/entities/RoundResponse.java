package com.tdtech.scorecardapi.round.entities;

import com.tdtech.scorecardapi.bow.entities.BowResponse;
import com.tdtech.scorecardapi.user.entities.UserResponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoundResponse {
    private String id;
    private UserResponse user;
    private BowResponse bow;
    private String roundType;
    private Date roundDate;
    private String location;
    private String notes;
    private List<EndResponse> ends;
    private int score;

    public RoundResponse(RoundDto round) {
        this.id = round.getId();
        if(round.getUser() != null) {
            this.user = new UserResponse(round.getUser());
        }
        if(round.getBow() != null) {
            this.bow = new BowResponse(round.getBow());
        }
        if(round.getEnds() != null) {
            List<EndResponse> endList = new ArrayList<EndResponse>();
            round.getEnds().forEach((e) -> {
                endList.add(new EndResponse(e));
            });
            this.ends = endList;
        }
        this.roundType = round.getRoundType();
        this.roundDate = round.getRoundDate();
        this.location = round.getLocation();
        this.notes = round.getNotes();
        this.score = round.getScore();
    }
}
