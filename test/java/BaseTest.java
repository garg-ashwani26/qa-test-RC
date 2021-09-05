import findalluser.FindAllUsersTest;
import model.response.findallusers.Content;
import model.response.findallusers.FindAllUsers;
import model.response.findallusers.Link;
import org.testng.annotations.BeforeTest;
import utility.BaseApi;
import utility.DBUtility;
import java.util.*;

public class BaseTest {

    ArrayList<LinkedHashMap<String,String>> listOfDataRows = new ArrayList<>();
    DBUtility dbUtility = new DBUtility();

    @BeforeTest
    public void beforeTest() {

        HashMap<String, String> headers = new HashMap();
        headers.put("content-type", "application/json");
        HashMap<String, String> params = new HashMap();
        String basePath = "/api/users";
        FindAllUsers findAllUsersResponse = new FindAllUsersTest().apiInvokeAndValidate(basePath,
                BaseApi.HTTP_METHOD.GET, headers, params, null);

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
}
