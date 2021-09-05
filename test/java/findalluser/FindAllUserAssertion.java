package findalluser;

import helper.findAllUsersHelper;
import io.restassured.response.Response;
import model.dbResult;
import model.response.findallusers.Content;
import model.response.findallusers.FindAllUsers;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindAllUserAssertion {

    SoftAssert softAssert = new SoftAssert();

    public void assertAPIResponseValid(Response apiResponse, ArrayList<dbResult> dbResultList)
    {
        findAllUsersHelper findAllUsersHelper = new findAllUsersHelper(apiResponse.asString());
        FindAllUsers findAllUsersDto = findAllUsersHelper.getResponseDto();

        if(apiResponse.getStatusCode() == 200) {
            //Validate every record from the API with database record
            List<Content> contentList = findAllUsersDto.getContent();
            if (dbResultList.size() == contentList.size()) {

                for(int i = 0; i < dbResultList.size(); i++)
                {
                    softAssert.assertEquals(contentList.get(i).getId(),dbResultList.get(i).getId(), "Validate ID field");
                    softAssert.assertEquals(contentList.get(i).getFirstName(),dbResultList.get(i).getFirstName(), "Validate firstName field");
                    softAssert.assertEquals(contentList.get(i).getLastName(),dbResultList.get(i).getLastName(), "Validate lastName field");
                    softAssert.assertEquals(contentList.get(i).getEmail(),dbResultList.get(i).getEmail(), "Validate email field");
                    softAssert.assertEquals(contentList.get(i).getDayOfBirth(),dbResultList.get(i).getDayOfBirth().toString(), "Validate dayOfBirth field");
                }
            }
            else
            {
                softAssert.fail("Mismatch in Content List Size: apiContentListSize = " + contentList.size() +
                        " dbResultListSize = " + dbResultList.size());
            }
        }
        else
        {
            softAssert.fail("200 API Status Code Failure");
        }
        softAssert.assertAll();

    }
}
