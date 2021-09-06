package saveuser;

import io.restassured.response.Response;
import model.dbResult;
import model.request.saveuser.SaveUser;
import utility.ApiHelperUtil;
import utility.BaseApi;
import utility.DBUtility;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SaveUserTestHelper {

    public int saveUserData(LinkedHashMap<String, String> params)
    {
        return new DBUtility().derbyInsertDataWithoutID(params);
    }

    public ArrayList<dbResult> fetchFirstRecord()
    {
        return new DBUtility().derbyFetchFirstRecord();
    }

    public SaveUser createRequestDto(LinkedHashMap<String, String> requestBodyParams)
    {
        SaveUser saveUserRequestDto = new SaveUser();
        saveUserRequestDto.setFirstName(requestBodyParams.get("firstName"));
        saveUserRequestDto.setLastName(requestBodyParams.get("lastName"));
        saveUserRequestDto.setEmail(requestBodyParams.get("email"));
        saveUserRequestDto.setDayOfBirth(requestBodyParams.get("dayOfBirth"));
        return saveUserRequestDto;
    }
}
