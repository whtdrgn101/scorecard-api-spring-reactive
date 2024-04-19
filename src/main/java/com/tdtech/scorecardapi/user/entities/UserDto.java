package com.tdtech.scorecardapi.user.entities;

import com.tdtech.scorecardapi.bow.entities.BowDto;
import com.tdtech.scorecardapi.bow.entities.BowRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<BowDto> bows;

    public UserDto(UserRequest user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        if(user.getBows() != null) {
            List<BowDto> bows = new ArrayList<>();
            user.getBows().forEach(bow -> {
                bows.add(new BowDto(bow));
            });
            this.bows = bows;
        }
    }

    public void fillBowsFromRequest(List<BowRequest> bowList) {
        if(bowList != null) {
            List<BowDto> nbows = new ArrayList<BowDto>();
            bowList.forEach((bow) -> {
                nbows.add(new BowDto(bow));
            });
            this.bows = nbows;
        } else {
            this.bows = new ArrayList<BowDto>();
        }
    }
}
