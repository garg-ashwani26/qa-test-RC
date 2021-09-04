package findalluser;

import helper.findAllUsersHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import utility.ApiHelperUtil;
import utility.BaseApi;

import java.util.Map;

public class FindAllUsersTest {

    public void apiInvokeAndValidate(String basePath, BaseApi.HTTP_METHOD httpMethod, Map<String, String> headers,
                                     Map<String, String> params, Object requestDTO)
    {

        Response response = ApiHelperUtil.invokeApi(basePath, httpMethod, headers, params, requestDTO);
        findAllUsersHelper helper = new findAllUsersHelper(response.asString());
        helper.testMethod();
        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
