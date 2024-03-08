package com.tdtech.scorecardapi;

import com.tdtech.scorecardapi.bow.entities.BowDto;
import com.tdtech.scorecardapi.bow.entities.BowRequest;
import com.tdtech.scorecardapi.round.entities.RoundDto;
import com.tdtech.scorecardapi.round.entities.RoundRequest;
import com.tdtech.scorecardapi.round.entities.RoundResponse;
import com.tdtech.scorecardapi.user.entities.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class RoundModelTests {

    @Test
    void shouldConvertRequestToDto() {
        List<BowDto> bows = new ArrayList<BowDto>();
        BowRequest bow = new BowRequest("Compound", "testbow1", "Hoyte", "Podium", 55.00, 38.00, 5.65);
        UserDto user = new UserDto("id1","testFirst", "testLast", "test@email.com", "password", bows);
        RoundRequest roundA = new RoundRequest("id1", bow, "tnfaa", new Date(), "here", "notes1",300);
        RoundDto roundB = new RoundDto(roundA, user);

        assertThat(roundB.getBow().getName()).isEqualTo(roundA.getBow().getName());
        assertThat(roundB.getRoundType()).isEqualTo(roundA.getRoundType());
        assertThat(roundB.getRoundDate()).isEqualTo(roundA.getRoundDate());
        assertThat(roundB.getLocation()).isEqualTo(roundA.getLocation());
        assertThat(roundB.getNotes()).isEqualTo(roundA.getNotes());
        assertThat(roundB.getScore()).isEqualTo(roundA.getScore());
        assertThat(roundB.getUser().getId()).isEqualTo(roundA.getUserId());
    }

    @Test
    void shouldConvertDtoToResponse() {
        List<BowDto> bows = new ArrayList<BowDto>();
        BowDto bow = new BowDto("Compound", "testbow1", "Hoyte", "Podium", 55.00, 38.00, 5.65);
        UserDto user = new UserDto("id1","testFirst", "testLast", "test@email.com", "password", bows);
        RoundDto roundA = new RoundDto("id1", user, bow, "nfaa", new Date(),"home","notes",500 );
        RoundResponse roundB = new RoundResponse(roundA);

        assertThat(roundB.getUser().getFirstName()).isEqualTo(roundB.getUser().getFirstName());
        assertThat(roundB.getBow().getName()).isEqualTo(roundA.getBow().getName());
        assertThat(roundB.getId()).isEqualTo(roundA.getId());
        assertThat(roundB.getRoundType()).isEqualTo(roundA.getRoundType());
        assertThat(roundB.getRoundDate()).isEqualTo(roundA.getRoundDate());
        assertThat(roundB.getLocation()).isEqualTo(roundA.getLocation());
        assertThat(roundB.getNotes()).isEqualTo(roundA.getNotes());
        assertThat(roundB.getScore()).isEqualTo(roundA.getScore());
        //assertThat(roundB.getUser().getId()).isEqualTo(roundA.getUserId());
    }
}
