package saveuser;

import io.restassured.response.Response;
import org.testng.Assert;
import utility.ApiHelperUtil;
import utility.BaseApi;

import java.util.Map;

public class SaveUserTest {

    public void apiInvokeAndValidate(String basePath, BaseApi.HTTP_METHOD httpMethod, Map<String, String> headers,
                                     Map<String, String> params, Object requestDTO)
    {

        Response response = ApiHelperUtil.invokeApi(basePath, httpMethod, headers, params, requestDTO);
        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
