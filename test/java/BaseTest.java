import helper.FindAllUsersHelper;
import io.restassured.response.Response;
import model.response.findallusers.Content;
import model.response.findallusers.FindAllUsers;
import org.testng.annotations.BeforeTest;
import utility.ApiHelperUtil;
import utility.BaseApi;
import utility.DBUtility;

import java.io.File;
import java.util.*;

public class BaseTest {

    ArrayList<LinkedHashMap<String,String>> listOfDataRows = new ArrayList<>();
    DBUtility dbUtility = new DBUtility();

    @BeforeTest
    public void beforeTest() {

        //delete existing derby database record
        deleteDirectory(new File (System.getProperty("user.dir") + File.separator + "ringcentral"));
        HashMap<String, String> headers = new HashMap();
        headers.put("content-type", "application/json");
        HashMap<String, String> params = new HashMap();
        String basePath = "/api/users";
//        FindAllUsers findAllUsersResponse = new FindAllUsersTestHelper().apiInvokeAndValidate(basePath,
//                BaseApi.HTTP_METHOD.GET, headers, params, null);
        Response response = ApiHelperUtil.invokeApi(basePath, BaseApi.HTTP_METHOD.GET, headers, params, null);
        FindAllUsersHelper helper = new FindAllUsersHelper(response.asString());
        FindAllUsers findAllUsersResponse =  helper.getResponseDto();

        for(Content content : findAllUsersResponse.getContent())
        {
            LinkedHashMap<String,String> recordMap = new LinkedHashMap<>();
            recordMap.put("id", String.valueOf(content.getId()));
            recordMap.put("firstName", content.getFirstName());
            recordMap.put("lastName", content.getLastName());
            recordMap.put("email", content.getEmail());
            recordMap.put("dayOfBirth", content.getDayOfBirth());
            listOfDataRows.add(recordMap);
        }
        dbUtility.derbyCreateTable();
        dbUtility.derbyInsertData(listOfDataRows);
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}
