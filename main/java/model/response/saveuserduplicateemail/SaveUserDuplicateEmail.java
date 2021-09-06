package model.response.saveuserduplicateemail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUserDuplicateEmail {

    public String status;
    public String timestamp;
    public String message;
    public String debugMessage;
    public Object subErrors;
}
