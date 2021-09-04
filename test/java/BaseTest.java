import findalluser.FindAllUsersTest;
import org.testng.annotations.BeforeTest;
import utility.BaseApi;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    @BeforeTest
    public void beforeTest() {

        //Executing the jar to initialize the API service
//        ProcessBuilder processBuilderObj =
//                new ProcessBuilder("java", "-jar", getClass().getClassLoader().
//                        getResource("test-rest-api.jar").getPath().toString().replace("/","//"));
//        Process processObj = processBuilderObj.start();

        HashMap<String, String> headers = new HashMap();
        headers.put("content-type", "application/json");
        HashMap<String, String> params = new HashMap();
        String basePath = "/api/users";
        new FindAllUsersTest().apiInvokeAndValidate(basePath, BaseApi.HTTP_METHOD.GET, headers, params, null);

    }

}
