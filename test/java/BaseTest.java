import helper.FindAllUsersHelper;
import io.restassured.response.Response;
import model.response.findallusers.Content;
import model.response.findallusers.FindAllUsers;
import org.testng.annotations.BeforeTest;
import utility.ApiHelperUtil;
import utility.BaseApi;
import utility.DBUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class BaseTest {

    ArrayList<LinkedHashMap<String,String>> listOfDataRows = new ArrayList<>();
    DBUtility dbUtility = new DBUtility();

    @BeforeTest
    public void beforeTest() {

        //delete existing derby database record
        deleteDirectory(new File (System.getProperty("user.dir") + File.separator + "ringcentral"));

        //Counter initialized to fetch initial 20 records
        int pageCounter = 0;
        FindAllUsers findAllUsersResponse = fetchDataFromFindAllUser(pageCounter);

        //Data fetched from API response and stored in class list variable
        createDataList(findAllUsersResponse);

        //Call to additional pages if exists and add additional data to class list variable
        while(++pageCounter < findAllUsersResponse.getPage().getTotalPages())
        {
            findAllUsersResponse = fetchDataFromFindAllUser(pageCounter);
            createDataList(findAllUsersResponse);
        }

        dbUtility.derbyCreateTable();
        dbUtility.derbyInsertDataList(listOfDataRows);
    }

    void createDataList(FindAllUsers findAllUsersResponse)
    {
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
    }

    FindAllUsers fetchDataFromFindAllUser(int Page)
    {
        //Set headers
        HashMap<String, String> headers = new HashMap();
        headers.put("content-type", "application/json");

        //Set URL Parameters
        HashMap<String, String> params = new HashMap();
        params.put("page",String.valueOf(Page));

        String basePath = "/api/users";

        //API Call to fetch default 20 records based on given Page number
        Response response = ApiHelperUtil.invokeApi(basePath, BaseApi.HTTP_METHOD.GET, headers, params, null);
        FindAllUsersHelper helper = new FindAllUsersHelper(response.asString());
        return  helper.getResponseDto();
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
