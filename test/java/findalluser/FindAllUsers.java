package findalluser;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.BaseApi;
import java.util.*;

public class FindAllUsers{

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
    public void testFindAllUsers(LinkedHashMap<String, String> params){
        
        Set<String> keys = params.keySet();
        for(String key : keys){
            if(params.get(key) != "NA")
            params.put(key, params.get(key));
        }

        new FindAllUsersTest().apiInvokeAndValidate(basePath, BaseApi.HTTP_METHOD.GET, headers, params, null);
    }

    Map<String, String> headersList()
    {
        HashMap<String, String> headers = new HashMap();
        headers.put("content-type", "application/json");
        return headers;
    }
}
