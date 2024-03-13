package com.tdtech.scorecardapi.entitytests;

import com.tdtech.scorecardapi.bow.entities.BowDto;
import com.tdtech.scorecardapi.bow.entities.BowRequest;
import com.tdtech.scorecardapi.round.entities.*;
import com.tdtech.scorecardapi.user.entities.UserDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoundModelTests {

    @Test
    void shouldConvertRequestToDto() {

        List<BowDto> bows = new ArrayList<BowDto>();
        List<EndRequest> ends = new ArrayList<EndRequest>();
        ends.add(Mockito.mock(EndRequest.class));
        ends.add(Mockito.mock(EndRequest.class));
        BowRequest bow = Mockito.mock(BowRequest.class);
        UserDto user = Mockito.mock(UserDto.class);
        Mockito.when(user.getId()).thenReturn("id1");
        RoundRequest roundA = new RoundRequest("id1", bow, "tnfaa", new Date(), "here", "notes1",ends, 300);
        RoundDto roundB = new RoundDto(roundA, user);

        assertThat(roundB.getBow().getName()).isEqualTo(roundA.getBow().getName());
        assertThat(roundB.getRoundType()).isEqualTo(roundA.getRoundType());
        assertThat(roundB.getRoundDate()).isEqualTo(roundA.getRoundDate());
        assertThat(roundB.getLocation()).isEqualTo(roundA.getLocation());
        assertThat(roundB.getNotes()).isEqualTo(roundA.getNotes());
        assertThat(roundB.getScore()).isEqualTo(roundA.getScore());
        assertThat(roundB.getEnds().size()).isEqualTo(roundA.getEnds().size());
        assertThat(roundB.getUser().getId()).isEqualTo(roundA.getUserId());
    }

    @Test
    void shouldConvertDtoToResponse() {
        List<BowDto> bows = new ArrayList<BowDto>();
        List<EndDto> ends = new ArrayList<EndDto>();
        ends.add(Mockito.mock(EndDto.class));
        ends.add(Mockito.mock(EndDto.class));
        BowDto bow = Mockito.mock(BowDto.class);
        UserDto user = Mockito.mock(UserDto.class);
        Mockito.when(user.getId()).thenReturn("id1");
        RoundDto roundA = new RoundDto("id1", user, bow, "nfaa", new Date(),"home","notes",ends, 500 );
        RoundResponse roundB = new RoundResponse(roundA);

        assertThat(roundB.getUser().getFirstName()).isEqualTo(roundB.getUser().getFirstName());
        assertThat(roundB.getBow().getName()).isEqualTo(roundA.getBow().getName());
        assertThat(roundB.getId()).isEqualTo(roundA.getId());
        assertThat(roundB.getRoundType()).isEqualTo(roundA.getRoundType());
        assertThat(roundB.getRoundDate()).isEqualTo(roundA.getRoundDate());
        assertThat(roundB.getLocation()).isEqualTo(roundA.getLocation());
        assertThat(roundB.getNotes()).isEqualTo(roundA.getNotes());
        assertThat(roundB.getScore()).isEqualTo(roundA.getScore());
        assertThat(roundB.getEnds().size()).isEqualTo(roundA.getEnds().size());
        assertThat(roundB.getUser().getId()).isEqualTo(roundA.getUser().getId());
    }
}
