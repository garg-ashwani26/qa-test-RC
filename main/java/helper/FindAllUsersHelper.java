package helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.response.findallusers.FindAllUsers;

public class FindAllUsersHelper {

     FindAllUsers findAllUsersDto;

    public FindAllUsersHelper(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            findAllUsersDto = mapper.readValue(response, FindAllUsers.class);
        }catch (JsonMappingException e)
        {
            e.printStackTrace();
        }catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
    }


    public FindAllUsers getResponseDto()
    {
        return findAllUsersDto;
    }

}
