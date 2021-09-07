package deleteuser;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class DeleteUserAssertion {

    SoftAssert softAssert = new SoftAssert();

    public void assertAPIResponseValid(Response apiResponse, int recordID, Response apiResponseGetUserByID)
    {
        if (apiResponse.getStatusCode() == 204 && apiResponseGetUserByID.getStatusCode() == 404) {
            System.out.println("Record ID " + recordID + " deleted from API data reference");
        }
        else {
            Assert.fail("API Status Code Failure : Actual " + apiResponse.getStatusCode() + " Expected 204");}

    }

    public void assertAPIResponseFailure(Response apiResponse)
    {
        if (apiResponse.getStatusCode() == 404) {
            System.out.println("Status as Expected : Not Found");
        }
        else {
            Assert.fail("API Status Code Failure : Actual " + apiResponse.getStatusCode() + " Expected 404");}
    }
}
