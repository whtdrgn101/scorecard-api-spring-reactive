package com.tdtech.scorecardapi.user.entities;

import com.tdtech.scorecardapi.bow.entities.BowDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private List<BowDto> bows;

    public UserResponse(UserDto user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.bows = user.getBows();
    }
}
