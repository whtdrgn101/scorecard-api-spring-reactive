package com.tdtech.scorecardapi.entitytests;

import com.tdtech.scorecardapi.round.entities.EndDto;
import com.tdtech.scorecardapi.round.entities.EndRequest;
import com.tdtech.scorecardapi.round.entities.EndResponse;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EndModelTests {

    @Test
    void shouldReturnDtoFromRequest() {
        EndRequest e = new EndRequest(1,15);
        EndDto endDto = new EndDto(e);

        assertThat(e.getSequence()).isEqualTo(endDto.getSequence());
        assertThat(e.getScore()).isEqualTo(endDto.getScore());
    }

    @Test
    void shouldReturnResponseFromDto() {
        EndDto endDto = new EndDto(1,15);
        EndResponse endResponse = new EndResponse(endDto);

        assertThat(endDto.getSequence()).isEqualTo(endResponse.getSequence());
        assertThat(endDto.getScore()).isEqualTo(endResponse.getScore());
    }
}
