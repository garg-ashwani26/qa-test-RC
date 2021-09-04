package helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.response.findallusers.findAllUsers;

public class findAllUsersHelper {

     findAllUsers findAllUsersDto;

    public findAllUsersHelper(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            findAllUsersDto = mapper.readValue(response, findAllUsers.class);
        }catch (JsonMappingException exp)
        {
            System.out.println("Json Mapping Exception: " + exp);
        }catch (JsonProcessingException exp)
        {
            System.out.println("Json Processing Exception: " + exp);
        }
    }

    public void testMethod()
    {
        System.out.println("Printing values: " + findAllUsersDto.getContent());
    }
}
