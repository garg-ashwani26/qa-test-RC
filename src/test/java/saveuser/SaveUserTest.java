package saveuser;

import io.restassured.response.Response;
import model.dbResult;
import model.request.saveuser.SaveUser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.ApiHelperUtil;
import utility.BaseApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SaveUserTest {

    HashMap<String, String> headers = new HashMap();
    HashMap<String, String> params = new HashMap();
    String basePath;

    @BeforeMethod
    public void initialize()
    {
        headers.putAll(headersList());
        basePath = "/api/users";
    }

    @Test(dataProvider = "SaveUserAPIData", dataProviderClass = SaveUserDataProvider.class)
    public void testSaveUserValid(LinkedHashMap<String, String> requestBodyParams){

        SaveUserTestHelper saveUserTestHelper = new SaveUserTestHelper();

        //Create Request Body Payload
        SaveUser saveUserRequestDto = saveUserTestHelper.createRequestDto(requestBodyParams);

        //Make API Call and get Response object
        Response response = ApiHelperUtil.invokeApi(basePath, BaseApi.HTTP_METHOD.POST, headers,
                params, saveUserRequestDto);

        //Save Record in Derby Database
        if(response.getStatusCode() == 201) {
            requestBodyParams.put("id", String.valueOf(response.jsonPath().getInt("id")));
            saveUserTestHelper.saveUserData(requestBodyParams);
        }

        //API Response Assertion
        new SaveUserAssertion().assertAPIResponseValid(response, requestBodyParams);
    }

    @Test(dataProvider = "SaveUserAPIData", dataProviderClass = SaveUserDataProvider.class)
    public void testSaveUserInvalidFirstName(LinkedHashMap<String, String> requestBodyParams){

        SaveUserTestHelper saveUserTestHelper = new SaveUserTestHelper();

        //Create Request Body Payload
        SaveUser saveUserRequestDto = saveUserTestHelper.createRequestDto(requestBodyParams);

        //Make API Call and get Response object
        Response response = ApiHelperUtil.invokeApi(basePath, BaseApi.HTTP_METHOD.POST, headers,
                params, saveUserRequestDto);

        //API Response Assertion
        new SaveUserAssertion().assertAPIResponseInvalidFirstName(response, requestBodyParams.get("firstName"));
    }

    @Test(dataProvider = "SaveUserAPIData", dataProviderClass = SaveUserDataProvider.class)
    public void testSaveUserInvalidLastName(LinkedHashMap<String, String> requestBodyParams){

        SaveUserTestHelper saveUserTestHelper = new SaveUserTestHelper();

        //Create Request Body Payload
        SaveUser saveUserRequestDto = saveUserTestHelper.createRequestDto(requestBodyParams);

        //Make API Call and get Response object
        Response response = ApiHelperUtil.invokeApi(basePath, BaseApi.HTTP_METHOD.POST, headers,
                params, saveUserRequestDto);

        //API Response Assertion
        new SaveUserAssertion().assertAPIResponseInvalidLastName(response, requestBodyParams.get("lastName"));
    }

    @Test(dataProvider = "SaveUserAPIData", dataProviderClass = SaveUserDataProvider.class)
    public void testSaveUserInvalidEmail(LinkedHashMap<String, String> requestBodyParams){

        SaveUserTestHelper saveUserTestHelper = new SaveUserTestHelper();

        //Create Request Body Payload
        SaveUser saveUserRequestDto = saveUserTestHelper.createRequestDto(requestBodyParams);

        //Make API Call and get Response object
        Response response = ApiHelperUtil.invokeApi(basePath, BaseApi.HTTP_METHOD.POST, headers,
                params, saveUserRequestDto);

        //API Response Assertion
        new SaveUserAssertion().assertAPIResponseInvalidEmail(response, requestBodyParams.get("email"));
    }

    @Test(dataProvider = "SaveUserAPIData", dataProviderClass = SaveUserDataProvider.class)
    public void testSaveUserInvalidDate(LinkedHashMap<String, String> requestBodyParams) {

        SaveUserTestHelper saveUserTestHelper = new SaveUserTestHelper();

        //Create Request Body Payload
        if(requestBodyParams.get("dayOfBirth").equals("getCurrentDate"))
        {
            requestBodyParams.replace("dayOfBirth", String.valueOf(java.time.LocalDate.now()));
        }
        SaveUser saveUserRequestDto = saveUserTestHelper.createRequestDto(requestBodyParams);

        //Make API Call and get Response object
        Response response = ApiHelperUtil.invokeApi(basePath, BaseApi.HTTP_METHOD.POST, headers,
                params, saveUserRequestDto);

        //API Response Assertion
        new SaveUserAssertion().assertAPIResponseInvalidDate(response, requestBodyParams.get("dayOfBirth"));
    }

    @Test(dataProvider = "SaveUserAPIData", dataProviderClass = SaveUserDataProvider.class)
    public void testSaveUserDuplicateEmail(LinkedHashMap<String, String> requestBodyParams){

        //Fetch First Record from Derby Database
        SaveUserTestHelper saveUserTestHelper = new SaveUserTestHelper();
        ArrayList<dbResult> firstRecord= saveUserTestHelper.fetchFirstRecord();

        //Replace email of the test data with first record
        requestBodyParams.replace("email",firstRecord.get(0).getEmail());

        //Create Request Body Payload
        SaveUser saveUserRequestDto = saveUserTestHelper.createRequestDto(requestBodyParams);

        //Make API Call and get Response object
        Response response = ApiHelperUtil.invokeApi(basePath, BaseApi.HTTP_METHOD.POST, headers,
                params, saveUserRequestDto);

        //API Response Assertion
        new SaveUserAssertion().assertAPIResponseDuplicateEmail(response);
    }

    //Method to set common headers
    Map<String, String> headersList()
    {
        HashMap<String, String> headers = new HashMap();
        headers.put("content-type", "application/json");
        return headers;
    }
}
