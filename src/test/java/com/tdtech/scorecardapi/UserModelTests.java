package com.tdtech.scorecardapi;

import com.tdtech.scorecardapi.bow.entities.BowDto;
import com.tdtech.scorecardapi.bow.entities.BowRequest;
import com.tdtech.scorecardapi.user.entities.UserDto;
import com.tdtech.scorecardapi.user.entities.UserRequest;
import com.tdtech.scorecardapi.user.entities.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserModelTests {
    @Test
    void shouldConvertRequestToDto() {
        List<BowRequest> bows = new ArrayList<BowRequest>();
        bows.add(new BowRequest("Compound", "testbow1", "Hoyte", "Podium", 55.00, 38.00, 5.65));
        UserRequest userA = new UserRequest("test", "user", "test@test.com", "abc123", bows);
        UserDto userB = new UserDto(userA);

        assertThat(userB.getBows().get(0).getName()).isEqualTo(userA.getBows().get(0).getName());
        assertThat(userB.getFirstName()).isEqualTo(userA.getFirstName());
        assertThat(userB.getLastName()).isEqualTo(userA.getLastName());
        assertThat(userB.getEmail()).isEqualTo(userA.getEmail());
        assertThat(userB.getBows().size()).isEqualTo(userA.getBows().size());
    }

    @Test
    void shouldConvertDtoToResponse() {
        List<BowDto> bows = new ArrayList<BowDto>();
        bows.add(new BowDto("Compound", "testbow1", "Hoyte", "Podium", 55.00, 38.00, 5.65));
        UserDto userA = new UserDto("id1", "test", "user", "test@test.com", "abc123", bows);
        UserResponse userB = new UserResponse(userA);

        assertThat(userB.getBows().get(0).getName()).isEqualTo(userA.getBows().get(0).getName());
        assertThat(userB.getId()).isEqualTo(userA.getId());
        assertThat(userB.getFirstName()).isEqualTo(userA.getFirstName());
        assertThat(userB.getLastName()).isEqualTo(userA.getLastName());
        assertThat(userB.getEmail()).isEqualTo(userA.getEmail());
        assertThat(userB.getBows().size()).isEqualTo(userA.getBows().size());
    }
}
