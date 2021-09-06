package helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.response.saveuser.SaveUser;

public class SaveUserHelper {

    SaveUser saveUsersDto;

    public SaveUserHelper(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            saveUsersDto = mapper.readValue(response, SaveUser.class);
        }catch (JsonMappingException e)
        {
            e.printStackTrace();
        }catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
    }


    public SaveUser getResponseDto()
    {
        return saveUsersDto;
    }
}
