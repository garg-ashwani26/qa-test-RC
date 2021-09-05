package saveuser;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.BaseApi;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class SaveUserTest {

    HashMap<String, String> headers = new HashMap();
    String basePath;

    @BeforeMethod
    public void initialize()
    {
        headers.putAll(headersList());
        basePath = "/api/users";
    }

    @Test(dataProvider = "SaveUserAPIData", dataProviderClass = SaveUserDataProvider.class)
    public void testFindAllUsers(LinkedHashMap<String, String> params){

        Set<String> keys = params.keySet();
        for(String key : keys){
            if(params.get(key) != "NA")
                params.put(key, params.get(key));
        }

        new SaveUserTestHelper().apiInvokeAndValidate(basePath, BaseApi.HTTP_METHOD.POST, headers, params, null);
    }

    Map<String, String> headersList()
    {
        HashMap<String, String> headers = new HashMap();
        headers.put("content-type", "application/json");
        return headers;
    }
}
