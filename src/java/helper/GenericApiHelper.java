package java.helper;

import java.Utils.BaseApi;
import java.util.Map;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.util.CollectionUtils;

public class GenericApiHelper {

    public static Response invokeApi(String basePath, BaseApi.HTTP_METHOD httpMethod, Map<String, String> headers,
                                     Map<String, Object> params, Object requestDTO) {

        BaseApi baseApi = new BaseApi();
        // Set the Request Method
        baseApi.setHttpMethod(httpMethod);

        // Set the Base URI and Path
        baseApi.getSpecBuilder().setBaseUri(baseApi.getBaseUri());
        baseApi.getSpecBuilder().setBasePath(basePath);
        // set parameters
        if (!CollectionUtils.isEmpty(params)) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                baseApi.getSpecBuilder().addParam(entry.getKey(), entry.getValue());
            }
        }
        //Set the headers
        if (!CollectionUtils.isEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                baseApi.getSpecBuilder().addHeader(entry.getKey(), entry.getValue());
            }
        }

        //Set Content Type
        baseApi.getSpecBuilder().setContentType(ContentType.JSON);
        //Set the body
        if (null != requestDTO)
            baseApi.getSpecBuilder().setBody(requestDTO);

        return baseApi.execute();
    }
}
