package helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.response.saveuserduplicateemail.SaveUserDuplicateEmail;

public class SaveUserDuplicateEmailHelper {

    SaveUserDuplicateEmail saveUserDuplicateEmailDto;

    public SaveUserDuplicateEmailHelper(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            saveUserDuplicateEmailDto = mapper.readValue(response, SaveUserDuplicateEmail.class);
        }catch (JsonMappingException e)
        {
            e.printStackTrace();
        }catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
    }


    public SaveUserDuplicateEmail getResponseDto()
    {
        return saveUserDuplicateEmailDto;
    }
}
