package findalluser;

import io.restassured.response.Response;
import model.dbResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.BaseApi;
import java.util.*;

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
    public void testFindAllUsersValid(LinkedHashMap<String, String> params){

        FindAllUsersTestHelper helper = new FindAllUsersTestHelper();
        ArrayList<dbResult> dbResultList= helper.fetchDbData(params);

        Set<String> keys = params.keySet();
        for(String key : keys){
            if(params.get(key) != "NA")
            params.put(key, params.get(key));
        }

         Response apiResponse = new FindAllUsersTestHelper().apiInvokeAndValidate(basePath,
                BaseApi.HTTP_METHOD.GET, headers, params, null);

        new FindAllUserAssertion().assertAPIResponseValid(apiResponse, dbResultList);
    }

    Map<String, String> headersList()
    {
        HashMap<String, String> headers = new HashMap();
        headers.put("content-type", "application/json");
        return headers;
    }


}
