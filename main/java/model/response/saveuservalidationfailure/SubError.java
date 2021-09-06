package model.response.saveuservalidationfailure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubError {

    public String object;
    public String field;
    public String rejectedValue;
    public String message;
}
