package helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.response.findallusers.FindAllUsers;

public class findAllUsersHelper {

     FindAllUsers findAllUsersDto;

    public findAllUsersHelper(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            findAllUsersDto = mapper.readValue(response, FindAllUsers.class);
        }catch (JsonMappingException exp)
        {
            System.out.println("Json Mapping Exception: " + exp);
        }catch (JsonProcessingException exp)
        {
            System.out.println("Json Processing Exception: " + exp);
        }
    }


    public FindAllUsers getResponseData()
    {
        return findAllUsersDto;
    }
}
