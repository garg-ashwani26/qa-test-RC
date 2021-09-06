package model.request.saveuser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUser {
    private String dayOfBirth;
    private String email;
    private String firstName;
    private String lastName;
}
