package helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.response.findallusersemptydata.FindAllUsersEmptyData;

public class FindAllUsersEmptySetHelper {

    FindAllUsersEmptyData findAllUsersEmptyDataDto;

    public FindAllUsersEmptySetHelper(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            findAllUsersEmptyDataDto = mapper.readValue(response, FindAllUsersEmptyData.class);
        }catch (JsonMappingException e)
        {
            e.printStackTrace();
        }catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
    }


    public FindAllUsersEmptyData getResponseDto()
    {
        return findAllUsersEmptyDataDto;
    }

}
