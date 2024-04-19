package com.tdtech.scorecardapi.user.entities;

import com.tdtech.scorecardapi.bow.entities.BowRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<BowRequest> bows;
}
