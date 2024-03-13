package com.tdtech.scorecardapi.entitytests;

import com.tdtech.scorecardapi.bow.entities.BowDto;
import com.tdtech.scorecardapi.bow.entities.BowRequest;
import com.tdtech.scorecardapi.bow.entities.BowResponse;
import com.tdtech.scorecardapi.user.entities.UserDto;
import com.tdtech.scorecardapi.user.entities.UserRequest;
import com.tdtech.scorecardapi.user.entities.UserResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserModelTests {
    @Test
    void shouldConvertRequestToDto() {
        List<BowRequest> bows = new ArrayList<>();
        BowRequest bowr = Mockito.mock(BowRequest.class);
        bows.add(bowr);
        Mockito.when(bowr.getName()).thenReturn("testBow");
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
        List<BowDto> bows = new ArrayList<>();
        BowDto bowDto = Mockito.mock(BowDto.class);
        Mockito.when(bowDto.getName()).thenReturn("testBow");
        bows.add(bowDto);
        UserDto userA = new UserDto("id1", "test", "user", "test@test.com", "abc123", bows);
        UserResponse userB = new UserResponse(userA);

        assertThat(userB.getBows().get(0).getName()).isEqualTo(userA.getBows().get(0).getName());
        assertThat(userB.getId()).isEqualTo(userA.getId());
        assertThat(userB.getFirstName()).isEqualTo(userA.getFirstName());
        assertThat(userB.getLastName()).isEqualTo(userA.getLastName());
        assertThat(userB.getEmail()).isEqualTo(userA.getEmail());
        assertThat(userB.getBows().size()).isEqualTo(userA.getBows().size());
    }

    @Test
    void shouldFillBowsWithArray() {
        UserDto user = new UserDto("id1","tim","test","test@email.com","testPass",null);
        List<BowRequest> bows = new ArrayList<>();
        bows.add(Mockito.mock(BowRequest.class));

        user.fillBowsFromRequest(bows);

        assertThat(user.getBows().size()).isEqualTo(bows.size());
    }

    @Test
    void shouldFillBowsSetNull() {
        UserDto user = new UserDto("id1","tim","test","test@email.com","testPass",null);
        user.fillBowsFromRequest(null);
        assertThat(user.getBows().size()).isEqualTo(0);
    }
}
