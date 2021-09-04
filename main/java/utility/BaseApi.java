package utility;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    private boolean httpsRequest;
    private String baseUri;
    private String port;
    private String basePath;
    private BaseApi.HTTP_METHOD httpMethod;
    private RequestSpecBuilder specBuilder;

    public BaseApi() {
        this.httpsRequest = ConstantsUtil.isHttpsRequired;
        this.baseUri = ConstantsUtil.Host;
        this.port = ConstantsUtil.Port;
        this.specBuilder = new RequestSpecBuilder();
    }

    public void setHttpsRequest(boolean httpsRequest) {
        this.httpsRequest = httpsRequest;
    }

    public void setHttpMethod(BaseApi.HTTP_METHOD method) {
        this.httpMethod = method;
    }

    public BaseApi.HTTP_METHOD getHttpMethod() {
        return this.httpMethod;
    }

    public RequestSpecBuilder getSpecBuilder() {
        return this.specBuilder;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getBasePath() {
        return this.basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public Response execute() {
        RequestSpecification specification = this.specBuilder.build();
        switch (this.httpMethod) {
            case POST:
                return RestAssured.given().spec(specification).when().post();
            case GET:
                return RestAssured.given().spec(specification).when().get();
            case PUT:
                return RestAssured.given().spec(specification).when().put();
            case DELETE:
                return RestAssured.given().spec(specification).when().delete();
            case PATCH:
                return RestAssured.given().spec(specification).when().patch();
            default:
                return null;
        }
    }

    public String getBaseUri() {
        String completeUri;
        if(this.httpsRequest) {
            completeUri = "https://".concat(this.baseUri);
        } else {
            completeUri = "http://".concat(this.baseUri);
        }

//        if (!(this.port.equals((Object) null) | this.port.equalsIgnoreCase("null"))) {
        if(this.port != null){
            completeUri = completeUri.concat(":").concat(this.port);
        }

        return completeUri;
    }


    public enum HTTP_METHOD {
        POST,
        GET,
        PUT,
        DELETE,
        PATCH;

        HTTP_METHOD() {
        }

    }
}
