package helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.response.saveuservalidationfailure.SaveUserValidationFailure;

public class SaveUserValidationFailureHelper {

    SaveUserValidationFailure saveUserValidationFailureDto;

    public SaveUserValidationFailureHelper(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            saveUserValidationFailureDto = mapper.readValue(response, SaveUserValidationFailure.class);
        }catch (JsonMappingException e)
        {
            e.printStackTrace();
        }catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
    }


    public SaveUserValidationFailure getResponseDto()
    {
        return saveUserValidationFailureDto;
    }
}
