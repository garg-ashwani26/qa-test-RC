package findalluser;

import helper.findAllUsersHelper;
import io.restassured.response.Response;
import model.response.findallusers.FindAllUsers;
import utility.ApiHelperUtil;
import utility.BaseApi;

import java.util.Map;

public class FindAllUsersTest{

    public FindAllUsers apiInvokeAndValidate(String basePath, BaseApi.HTTP_METHOD httpMethod, Map<String, String> headers,
                                             Map<String, String> params, Object requestDTO)
    {
        Response response = ApiHelperUtil.invokeApi(basePath, httpMethod, headers, params, requestDTO);
        findAllUsersHelper helper = new findAllUsersHelper(response.asString());
        return helper.getResponseData();
    }
}
