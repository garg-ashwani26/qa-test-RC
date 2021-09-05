package model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class dbResult {

    int id;
    String firstName;
    String lastName;
    String email;
    Date dayOfBirth;

    public dbResult(int id, String firstName, String lastName, String email, Date dayOfBirth)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dayOfBirth = dayOfBirth;
    }
}
