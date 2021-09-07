package saveuser;

import helper.SaveUserDuplicateEmailHelper;
import helper.SaveUserHelper;
import helper.SaveUserValidationFailureHelper;
import io.restassured.response.Response;
import model.response.saveuser.SaveUser;
import model.response.saveuserduplicateemail.SaveUserDuplicateEmail;
import model.response.saveuservalidationfailure.SaveUserValidationFailure;
import model.response.saveuservalidationfailure.SubError;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.LinkedHashMap;
import java.util.List;

public class SaveUserAssertion {

    SoftAssert softAssert = new SoftAssert();

    public void assertAPIResponseValid(Response apiResponse, LinkedHashMap<String, String> params)
    {
        SaveUserHelper saveUserHelper = new SaveUserHelper(apiResponse.asString());
        SaveUser saveUserDto = saveUserHelper.getResponseDto();
        if(saveUserDto != null) {
            if (apiResponse.getStatusCode() == 201) {
                softAssert.assertEquals(saveUserDto.getId(), Integer.parseInt(params.get("id")), "Validate id field");
                System.out.println("Actual ID: " + saveUserDto.getId() + "\nExpected ID: " + params.get("id"));
                softAssert.assertEquals(saveUserDto.getFirstName(), params.get("firstName"), "Validate firstName field");
                System.out.println("Actual firstName: " + saveUserDto.getFirstName() + "\nExpected firstName: " + params.get("firstName"));
                softAssert.assertEquals(saveUserDto.getLastName(), params.get("lastName"), "Validate lastName field");
                System.out.println("Actual lastName: " + saveUserDto.getLastName() + "\nExpected lastName: " + params.get("lastName"));
                softAssert.assertEquals(saveUserDto.getEmail(), params.get("email"), "Validate email field");
                System.out.println("Actual email: " + saveUserDto.getEmail() + "\nExpected email : " + params.get("email"));
                softAssert.assertEquals(saveUserDto.getDayOfBirth(), params.get("dayOfBirth"), "Validate dayOfBirth field");
                System.out.println("Actual dayOfBirth: " + saveUserDto.getDayOfBirth() + "\nExpected dayOfBirth: " + params.get("dayOfBirth"));
            }
            else {
                Assert.fail("API Status Code Failure : Expected 201");}
        }
        else
        {Assert.fail("Failure in JSON response mapping");}
        softAssert.assertAll();
    }

    public void assertAPIResponseInvalidFirstName(Response apiResponse, String rejectedValue)
    {
        SaveUserValidationFailureHelper saveUserValidationFailureHelper = new SaveUserValidationFailureHelper(apiResponse.asString());
        SaveUserValidationFailure saveUserValidationFailureDto = saveUserValidationFailureHelper.getResponseDto();
        if(saveUserValidationFailureDto != null) {
            if (apiResponse.getStatusCode() == 400) {

                softAssert.assertEquals(saveUserValidationFailureDto.getStatus(), "BAD_REQUEST", "Validate status field");
                System.out.println("Actual Status: " + saveUserValidationFailureDto.getStatus() + "\nExpected Status: BAD_REQUEST");
                softAssert.assertEquals(saveUserValidationFailureDto.getMessage(), "Validation failed", "Validate message field");
                System.out.println("\nActual Message: " + saveUserValidationFailureDto.getMessage() + "\nExpected ID: Validation failed");

                List<SubError> subErrorArrayList = saveUserValidationFailureDto.getSubErrors();
                softAssert.assertEquals(subErrorArrayList.get(0).getObject(), "User", "Validate Object field");
                System.out.println("\nActual Object: " + subErrorArrayList.get(0).getObject() + "\nExpected Object: User");
                softAssert.assertEquals(subErrorArrayList.get(0).getField(), "firstName", "Validate Field value");
                System.out.println("\nActual Field: " + subErrorArrayList.get(0).getField() + "\nExpected Field: firstName");
                softAssert.assertEquals(subErrorArrayList.get(0).getMessage(), "size must be between 2 and 30", "Validate error message");
                System.out.println("\nActual Message: " + subErrorArrayList.get(0).getMessage() + "\nExpected Message: size must be between 2 and 30");
                softAssert.assertEquals(subErrorArrayList.get(0).getRejectedValue(), rejectedValue, "Validate rejected value");
                System.out.println("\nActual Rejected Value: " + subErrorArrayList.get(0).getRejectedValue() + "\nExpected Rejected Value: " + rejectedValue);
            }
            else {
                Assert.fail("API Status Code Failure : Expected 400");}
        }
        else
        {Assert.fail("Failure in JSON response mapping");}
        softAssert.assertAll();
    }

    public void assertAPIResponseInvalidLastName(Response apiResponse, String rejectedValue)
    {
        SaveUserValidationFailureHelper saveUserValidationFailureHelper = new SaveUserValidationFailureHelper(apiResponse.asString());
        SaveUserValidationFailure saveUserValidationFailureDto = saveUserValidationFailureHelper.getResponseDto();
        if(saveUserValidationFailureDto != null) {
            if (apiResponse.getStatusCode() == 400) {

                softAssert.assertEquals(saveUserValidationFailureDto.getStatus(), "BAD_REQUEST", "Validate status field");
                System.out.println("Actual Status: " + saveUserValidationFailureDto.getStatus() + "\nExpected Status: BAD_REQUEST");
                softAssert.assertEquals(saveUserValidationFailureDto.getMessage(), "Validation failed", "Validate message field");
                System.out.println("\nActual Message: " + saveUserValidationFailureDto.getMessage() + "\nExpected ID: Validation failed");

                List<SubError> subErrorArrayList = saveUserValidationFailureDto.getSubErrors();
                softAssert.assertEquals(subErrorArrayList.get(0).getObject(), "User", "Validate Object field");
                System.out.println("\nActual Object: " + subErrorArrayList.get(0).getObject() + "\nExpected Object: User");
                softAssert.assertEquals(subErrorArrayList.get(0).getField(), "lastName", "Validate Field value");
                System.out.println("\nActual Field: " + subErrorArrayList.get(0).getField() + "\nExpected Field: lastName");
                softAssert.assertEquals(subErrorArrayList.get(0).getMessage(), "size must be between 2 and 15", "Validate error message");
                System.out.println("\nActual Message: " + subErrorArrayList.get(0).getMessage() + "\nExpected Message: size must be between 2 and 15");
                softAssert.assertEquals(subErrorArrayList.get(0).getRejectedValue(), rejectedValue, "Validate rejected value");
                System.out.println("\nActual Rejected Value: " + subErrorArrayList.get(0).getRejectedValue() + "\nExpected Rejected Value: " + rejectedValue);
            }
            else {
                Assert.fail("API Status Code Failure : Expected 400");}
        }
        else
        {Assert.fail("Failure in JSON response mapping");}
        softAssert.assertAll();
    }

    public void assertAPIResponseInvalidEmail(Response apiResponse, String rejectedValue)
    {
        SaveUserValidationFailureHelper saveUserValidationFailureHelper = new SaveUserValidationFailureHelper(apiResponse.asString());
        SaveUserValidationFailure saveUserValidationFailureDto = saveUserValidationFailureHelper.getResponseDto();
        if(saveUserValidationFailureDto != null) {
            if (apiResponse.getStatusCode() == 400) {

                softAssert.assertEquals(saveUserValidationFailureDto.getStatus(), "BAD_REQUEST", "Validate status field");
                System.out.println("Actual Status: " + saveUserValidationFailureDto.getStatus() + "\nExpected Status: BAD_REQUEST");
                softAssert.assertEquals(saveUserValidationFailureDto.getMessage(), "Validation failed", "Validate message field");
                System.out.println("\nActual Message: " + saveUserValidationFailureDto.getMessage() + "\nExpected ID: Validation failed");

                List<SubError> subErrorArrayList = saveUserValidationFailureDto.getSubErrors();
                softAssert.assertEquals(subErrorArrayList.get(0).getObject(), "User", "Validate Object field");
                System.out.println("\nActual Object: " + subErrorArrayList.get(0).getObject() + "\nExpected Object: User");
                softAssert.assertEquals(subErrorArrayList.get(0).getField(), "email", "Validate Field value");
                System.out.println("\nActual Field: " + subErrorArrayList.get(0).getField() + "\nExpected Field: email");
                softAssert.assertEquals(subErrorArrayList.get(0).getMessage(), "must be a well-formed email address", "Validate error message");
                System.out.println("\nActual Message: " + subErrorArrayList.get(0).getMessage() + "\nExpected Message: must be a well-formed email address");
                softAssert.assertEquals(subErrorArrayList.get(0).getRejectedValue(), rejectedValue, "Validate rejected value");
                System.out.println("\nActual Rejected Value: " + subErrorArrayList.get(0).getRejectedValue() + "\nExpected Rejected Value: " + rejectedValue);
            }
            else {
                Assert.fail("API Status Code Failure : Expected 400");}
        }
        else
        {Assert.fail("Failure in JSON response mapping");}
        softAssert.assertAll();
    }

    public void assertAPIResponseInvalidDate(Response apiResponse, String rejectedValue)
    {
        SaveUserValidationFailureHelper saveUserValidationFailureHelper = new SaveUserValidationFailureHelper(apiResponse.asString());
        SaveUserValidationFailure saveUserValidationFailureDto = saveUserValidationFailureHelper.getResponseDto();
        if(saveUserValidationFailureDto != null) {
            if (apiResponse.getStatusCode() == 400) {

                softAssert.assertEquals(saveUserValidationFailureDto.getStatus(), "BAD_REQUEST", "Validate status field");
                System.out.println("Actual Status: " + saveUserValidationFailureDto.getStatus() + "\nExpected Status: BAD_REQUEST");
                softAssert.assertEquals(saveUserValidationFailureDto.getMessage(), "Validation failed", "Validate message field");
                System.out.println("\nActual Message: " + saveUserValidationFailureDto.getMessage() + "\nExpected ID: Validation failed");

                List<SubError> subErrorArrayList = saveUserValidationFailureDto.getSubErrors();
                softAssert.assertEquals(subErrorArrayList.get(0).getObject(), "User", "Validate Object field");
                System.out.println("\nActual Object: " + subErrorArrayList.get(0).getObject() + "\nExpected Object: User");
                softAssert.assertEquals(subErrorArrayList.get(0).getField(), "dayOfBirth", "Validate Field value");
                System.out.println("\nActual Field: " + subErrorArrayList.get(0).getField() + "\nExpected Field: dayOfBirth");
                softAssert.assertEquals(subErrorArrayList.get(0).getMessage(), "must be a past date", "Validate error message");
                System.out.println("\nActual Message: " + subErrorArrayList.get(0).getMessage() + "\nExpected Message: must be a past date");
                softAssert.assertEquals(subErrorArrayList.get(0).getRejectedValue(), rejectedValue, "Validate rejected value");
                System.out.println("\nActual Rejected Value: " + subErrorArrayList.get(0).getRejectedValue() + "\nExpected Rejected Value: " + rejectedValue);
            }
            else {
                Assert.fail("API Status Code Failure : Expected 400");}
        }
        else
        {Assert.fail("Failure in JSON response mapping");}
        softAssert.assertAll();
    }

    public void assertAPIResponseDuplicateEmail(Response apiResponse)
    {
        SaveUserDuplicateEmailHelper saveUserValidationFailureHelper = new SaveUserDuplicateEmailHelper(apiResponse.asString());
        SaveUserDuplicateEmail saveUserDuplicateEmailDto = saveUserValidationFailureHelper.getResponseDto();
        if(saveUserDuplicateEmailDto != null) {
            if (apiResponse.getStatusCode() == 409) {

                softAssert.assertEquals(saveUserDuplicateEmailDto.getStatus(), "CONFLICT", "Validate status field");
                System.out.println("Actual Status: " + saveUserDuplicateEmailDto.getStatus() + "\nExpected Status: CONFLICT");
                softAssert.assertEquals(saveUserDuplicateEmailDto.getMessage(), "Database error", "Validate message field");
                System.out.println("\nActual Message: " + saveUserDuplicateEmailDto.getMessage() + "\nExpected ID: Database error");
            }
            else {
                Assert.fail("API Status Code Failure : Expected 409");}
        }
        else
        {Assert.fail("Failure in JSON response mapping");}
        softAssert.assertAll();
    }
}
