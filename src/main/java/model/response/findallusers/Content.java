package model.response.findallusers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Content {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String dayOfBirth;
    private List<Object> content = null;
    public List<Link__1> links = null;
}
