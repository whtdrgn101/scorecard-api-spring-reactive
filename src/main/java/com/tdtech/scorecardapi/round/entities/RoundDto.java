package com.tdtech.scorecardapi.round.entities;

import com.tdtech.scorecardapi.bow.entities.BowDto;
import com.tdtech.scorecardapi.user.entities.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Data
@Document(collection="rounds")
@NoArgsConstructor
@AllArgsConstructor
public class RoundDto {
    @Id
    private String id;
    @DocumentReference(lazy = false)
    private UserDto user;
    private BowDto bow;
    private String roundType;
    private Date roundDate;
    private String location;
    private String notes;
    private int score;

    public RoundDto(RoundRequest round, UserDto user) {
        if(user != null) {
            this.user = user;
        }
        if(round.getBow() != null) {
            this.bow = new BowDto(round.getBow());
        }
        this.roundType = round.getRoundType();
        this.roundDate = round.getRoundDate();
        this.location = round.getLocation();
        this.notes = round.getNotes();
        this.score = round.getScore();
    }
}
