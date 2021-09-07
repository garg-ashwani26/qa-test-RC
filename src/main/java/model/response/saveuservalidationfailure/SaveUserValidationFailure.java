package model.response.saveuservalidationfailure;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveUserValidationFailure {

    public String status;
    public String timestamp;
    public String message;
    public Object debugMessage;
    public List<SubError> subErrors = null;
}
