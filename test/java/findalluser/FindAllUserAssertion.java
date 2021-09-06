package findalluser;

import helper.FindAllUsersEmptySetHelper;
import helper.FindAllUsersHelper;
import io.restassured.response.Response;
import model.dbResult;
import model.response.findallusers.Content;
import model.response.findallusers.FindAllUsers;
import model.response.findallusersemptydata.FindAllUsersEmptyData;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class FindAllUserAssertion {

    SoftAssert softAssert = new SoftAssert();

    public void assertAPIResponseValid(Response apiResponse, ArrayList<dbResult> dbResultList)
    {
        FindAllUsersHelper findAllUsersHelper = new FindAllUsersHelper(apiResponse.asString());
        FindAllUsers findAllUsersDto = findAllUsersHelper.getResponseDto();

        if(findAllUsersDto != null) {
            if (apiResponse.getStatusCode() == 200) {
                //Validate every record from the API with database record
                List<Content> contentList = findAllUsersDto.getContent();
                if (dbResultList.size() == contentList.size()) {

                    for (int i = 0; i < dbResultList.size(); i++) {
                        System.out.println("\n\nNew Record:\n");
                        softAssert.assertEquals(contentList.get(i).getId(), dbResultList.get(i).getId(), "Validate ID field");
                        System.out.println("Actual ID: " + contentList.get(i).getId() + " Expected ID: " + dbResultList.get(i).getId());
                        softAssert.assertEquals(contentList.get(i).getFirstName(), dbResultList.get(i).getFirstName(), "Validate firstName field");
                        System.out.println("Actual firstName: " + contentList.get(i).getFirstName() + " Expected firstName: " + dbResultList.get(i).getFirstName());
                        softAssert.assertEquals(contentList.get(i).getLastName(), dbResultList.get(i).getLastName(), "Validate lastName field");
                        System.out.println("Actual lastName: " + contentList.get(i).getLastName() + " Expected lastName: " + dbResultList.get(i).getLastName());
                        softAssert.assertEquals(contentList.get(i).getEmail(), dbResultList.get(i).getEmail(), "Validate email field");
                        System.out.println("Actual email: " + contentList.get(i).getEmail() + " Expected email: " + dbResultList.get(i).getEmail());
                        softAssert.assertEquals(contentList.get(i).getDayOfBirth(), dbResultList.get(i).getDayOfBirth().toString(), "Validate dayOfBirth field");
                        System.out.println("Actual dayOfBirth: " + contentList.get(i).getDayOfBirth() + " Expected dayOfBirth: " + dbResultList.get(i).getDayOfBirth());
                    }
                } else {
                    Assert.fail("Mismatch in Content List Size: apiContentListSize = " + contentList.size() +
                            " dbResultListSize = " + dbResultList.size());
                }
            } else {
                Assert.fail("API Status Code Failure : Expected 200");
            }
        }
        else
        {
            Assert.fail("Failure in JSON response mapping");
        }
        softAssert.assertAll();
    }

    public void assertAPIResponseEmptyDataset(Response apiResponse, ArrayList<dbResult> dbResultList)
    {
        FindAllUsersEmptySetHelper findAllUsersEmptySetHelper = new FindAllUsersEmptySetHelper(apiResponse.asString());
        FindAllUsersEmptyData findAllUsersEmptyDataDto = findAllUsersEmptySetHelper.getResponseDto();

        if(findAllUsersEmptyDataDto != null) {
            if (apiResponse.getStatusCode() == 200) {
            } else {
                Assert.fail("API Status Code Failure : Expected 200");
            }
        }
        else
        {
            Assert.fail("Failure in JSON response mapping");
        }
        softAssert.assertAll();

    }
}
