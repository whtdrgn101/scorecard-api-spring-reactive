package com.tdtech.scorecardapi.entitytests;

import com.tdtech.scorecardapi.bow.entities.BowDto;
import com.tdtech.scorecardapi.bow.entities.BowRequest;
import com.tdtech.scorecardapi.bow.entities.BowResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BowModelTests {

    @Test
    void shouldConvertRequestToDto() {
        BowRequest bowA = new BowRequest("compound","testBowName","testBowMan","testBowModel",55.0,38.0,7.25);
        BowDto bowB = new BowDto(bowA);

        assertThat(bowB.getName()).isEqualTo(bowA.getName());
        assertThat(bowB.getBowType()).isEqualTo(bowA.getBowType());
        assertThat(bowB.getManufacturer()).isEqualTo(bowA.getManufacturer());
        assertThat(bowB.getModel()).isEqualTo(bowA.getModel());
        assertThat(bowB.getDrawWeight()).isEqualTo(bowA.getDrawWeight());
        assertThat(bowB.getAtaLength()).isEqualTo(bowA.getAtaLength());
        assertThat(bowB.getBraceHeight()).isEqualTo(bowA.getBraceHeight());
    }

    @Test
    void shouldConvertDtoToResponse() {
        BowDto bowA = Mockito.mock(BowDto.class);
        BowResponse bowB = new BowResponse(bowA);

        assertThat(bowB.getName()).isEqualTo(bowA.getName());
        assertThat(bowB.getBowType()).isEqualTo(bowA.getBowType());
        assertThat(bowB.getManufacturer()).isEqualTo(bowA.getManufacturer());
        assertThat(bowB.getModel()).isEqualTo(bowA.getModel());
        assertThat(bowB.getDrawWeight()).isEqualTo(bowA.getDrawWeight());
        assertThat(bowB.getAtaLength()).isEqualTo(bowA.getAtaLength());
        assertThat(bowB.getBraceHeight()).isEqualTo(bowA.getBraceHeight());

    }
}
