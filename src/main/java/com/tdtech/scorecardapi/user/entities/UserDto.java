package com.tdtech.scorecardapi.user.entities;

import com.tdtech.scorecardapi.bow.entities.BowDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Data
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
}
