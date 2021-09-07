package model.response.saveuser;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveUser {
    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public String dayOfBirth;
    public List<Object> content = null;
    public List<Link> links = null;
}
