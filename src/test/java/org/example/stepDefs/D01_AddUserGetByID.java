package org.example.stepDefs;

import com.fawry.TicketsMall.angularAutomation.constants.GeneralConstants;
import com.fawry.TicketsMall.angularAutomation.tests.BaseTest;
import com.fawry.TicketsMall.angularAutomation.utils.Log;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.pages.P01_AddUserGetByID;
import org.testng.asserts.SoftAssert;


public class D01_AddUserGetByID extends BaseTest {

    public String actualResults;
    public String responseID;
    P01_AddUserGetByID P01_AddUserGetByID = new P01_AddUserGetByID();
    public Response addUserResponse;
    public Response responseGet;
    public String expectedFirstNamePostReq = "loai";


    @When("fire add user request and print the ID")
    public void fireAddUserReq()
    {



             addUserResponse = P01_AddUserGetByID.addUser(expectedFirstNamePostReq);
             responseID = addUserResponse.then().extract().path("id");
            System.out.println("response Id is " +responseID);

    }


    @And("get user by ID")
    public void getUserByID()
    {


         responseGet = P01_AddUserGetByID.getUserByID(responseID);



    }

    @Then("assert on status code 201 for add user")
    public void assertOnStatusCode()
    {

        String ExpectedStatusCode = "201";
        String ActualStatusCode = String.valueOf(addUserResponse.getStatusCode());
        SoftAssert AssertionObj = new SoftAssert();
        AssertionObj.assertEquals(ActualStatusCode, ExpectedStatusCode);
        AssertionObj.assertAll();


    } @And("assert on first name on \"get user by ID\" response")
    public void assertOnFirstName()
    {

        SoftAssert AssertionObj = new SoftAssert();

        //    Response responseGet = P01_AddUserGetByID.getUserByID(responseID);
        String actualResponseFirstName = responseGet.then().extract().path("profile.firstName");
        AssertionObj.assertEquals(actualResponseFirstName, expectedFirstNamePostReq);
        AssertionObj.assertAll();
    }
}
