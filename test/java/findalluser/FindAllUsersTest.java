package findalluser;

import io.restassured.response.Response;
import model.dbResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.ApiHelperUtil;
import utility.BaseApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FindAllUsersTest {

    HashMap<String, String> headers = new HashMap();
    HashMap<String, String> params = new HashMap();
    String basePath;

    @BeforeMethod
    public void initialize()
    {
        headers.putAll(headersList());
        basePath = "/api/users";
    }

    @Test(dataProvider = "FindAllUsersAPIData", dataProviderClass = FindAllUsersDataProvider.class)
    public void testFindAllUsersValid(LinkedHashMap<String, String> paramsData){

        FindAllUsersTestHelper helper = new FindAllUsersTestHelper();
        ArrayList<dbResult> dbResultList= helper.fetchDbData(paramsData);

        //Get only required request parameters from available list
        params = helper.getParamsMap(paramsData);

        //Make API Call and get Response object
         Response apiResponse = ApiHelperUtil.invokeApi(basePath,
                BaseApi.HTTP_METHOD.GET, headers, params, null);

        //API Response Assertion
        new FindAllUserAssertion().assertAPIResponseValid(apiResponse, dbResultList);
    }

    @Test()
    public void testFindAllUsersEmptyDataset(){

        LinkedHashMap<String,String> paramsData = new LinkedHashMap<>();
        FindAllUsersTestHelper helper = new FindAllUsersTestHelper();
        paramsData.put("page", String.valueOf(helper.dbFetchLastID()/20 + 1));
        paramsData.put("size", "NA");
        paramsData.put("sort", "NA");
        ArrayList<dbResult> dbResultList= helper.fetchDbData(paramsData);

        //Get only required request parameters from available list
        params = helper.getParamsMap(paramsData);

        //Make API Call and get Response object
        Response apiResponse = ApiHelperUtil.invokeApi(basePath,
                BaseApi.HTTP_METHOD.GET, headers, params, null);

        //API Response Assertion
        new FindAllUserAssertion().assertAPIResponseEmptyDataset(apiResponse, dbResultList);
    }

    //Method to set common headers
    Map<String, String> headersList()
    {
        HashMap<String, String> headers = new HashMap();
        headers.put("content-type", "application/json");
        return headers;
    }


}
