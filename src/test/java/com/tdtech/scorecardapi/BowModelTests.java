package com.tdtech.scorecardapi;

import com.tdtech.scorecardapi.bow.entities.BowDto;
import com.tdtech.scorecardapi.bow.entities.BowRequest;
import com.tdtech.scorecardapi.bow.entities.BowResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class BowModelTests {

    @Test
    void shouldConvertRequestToDto() {
        BowRequest bowA = Mockito.mock(BowRequest.class);
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
