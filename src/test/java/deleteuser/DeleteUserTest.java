package deleteuser;

import io.restassured.response.Response;
import model.dbResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.ApiHelperUtil;
import utility.BaseApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeleteUserTest {

    HashMap<String, String> headers = new HashMap();
    HashMap<String, String> params = new HashMap();
    String basePath;

    @BeforeMethod
    public void initialize()
    {
        headers.putAll(headersList());
        basePath = "/api/users";
    }

    @Test()
    public void testDeleteUserValid(){

        //Fetch First Record from Derby Database
        DeleteUserTestHelper deleteUserTestHelper = new DeleteUserTestHelper();
        ArrayList<dbResult> firstRecord= deleteUserTestHelper.fetchFirstRecord();

        //Get only required request parameters from available list
        int RecordId = firstRecord.get(0).getId();

        //Set ID to the end of the request URL
        String localBasePath = basePath + "/" + RecordId;

        //Make API Call and get Response object
        Response apiResponse = ApiHelperUtil.invokeApi(localBasePath,
                BaseApi.HTTP_METHOD.DELETE, headers, params, null);

        //Make Get User By ID API call to validate if user is deleted!
        Response apiResponseGetUserByID = ApiHelperUtil.invokeApi(localBasePath, BaseApi.HTTP_METHOD.GET,
                headers, params, null);

        //Delete record from derby database to keep it consistent with API data reference
        if (apiResponse.getStatusCode() == 204 && apiResponseGetUserByID.getStatusCode() == 404){
            deleteUserTestHelper.dbDeleteRow(RecordId);
        }

        //API Response Assertion
        new DeleteUserAssertion().assertAPIResponseValid(apiResponse, RecordId, apiResponseGetUserByID);
    }

    @Test
    public void testDeleteUserInvalid(){

        //Fetch Last RecordID from Derby Database
        DeleteUserTestHelper deleteUserTestHelper = new DeleteUserTestHelper();
        int lastRecordID = deleteUserTestHelper.dbFetchLastID();
        System.out.println("Record ID using for deletion: " + (lastRecordID + 1));

        String localBasePath = basePath + "/" + (lastRecordID + 1);

        //Make API Call and get Response object
        Response apiResponse = ApiHelperUtil.invokeApi(localBasePath,
                BaseApi.HTTP_METHOD.DELETE, headers, params, null);

        //API Response Assertion
        new DeleteUserAssertion().assertAPIResponseFailure(apiResponse);
    }

    @Test
    public void testDeleteUserMissingRequiredParam(){

        params = null;

        //Make API Call and get Response object
        Response apiResponse = ApiHelperUtil.invokeApi(basePath,
                BaseApi.HTTP_METHOD.DELETE, headers, params, null);

        //API Response Assertion
        new DeleteUserAssertion().assertAPIResponseFailure(apiResponse);
    }

    //Method to set common headers
    Map<String, String> headersList()
    {
        HashMap<String, String> headers = new HashMap();
        headers.put("content-type", "application/json");
        return headers;
    }
}
