package org.example.pages;

import com.fawry.TicketsMall.angularAutomation.constants.GeneralConstants;
import com.fawry.TicketsMall.angularAutomation.pages.MainPage;
import com.fawry.TicketsMall.angularAutomation.utils.Log;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.LinkedList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class P01_AddUserGetByID extends MainPage {
    public P01_AddUserGetByID() {


        RestAssured.registerParser("application/json, text/plain, */*", Parser.JSON);
        RestAssured.defaultParser = Parser.JSON;


    }


    public Response addUser(String firstName) {

        JSONObject payload = new JSONObject();
        this.AddOJsonObjPayloadForAddUser(payload,firstName);
        Log.info("sending Add api " + payload);
        Response response = given().body("["+payload+"]").when().log()
                .all().post("https://620e3da1585fbc3359db4edf.mockapi.io/api/v1/users");
        this.RespondAPILog(response);
        return response;


    }
    public Response getUserByID(String ID) {

        Response response = given().body("").when().log()
                .all().get("https://620e3da1585fbc3359db4edf.mockapi.io/api/v1/users/"+ID);
        this.RespondAPILog(response);
        return response;
    }
    public void RespondAPILog(Response response){
        Log.info("API response with " + response.getBody().asString());}


    public void AddOJsonObjPayloadForAddUser( JSONObject payload,String firstName){


        String name = "wedad";
        String username = "wedadkamal";
        // String firstName = "wedad";
        String lastName = "kamal";


        HandlingJsonObj("name", name, payload);
        HandlingJsonObj("username", username, payload);
        JSONObject profile = new JSONObject();
        profile.put("firstName", firstName);
        profile.put("lastName", lastName);

        List<Object> orders = new LinkedList<>();
        ParentJsonObjListadd(profile,"orders",orders);
        payload.put("profile",profile);



    }

    void HandlingJsonObj(String Key, String Value, JSONObject JSOBJ) {

        if (Value != "") {
            JSOBJ.put(Key, Value);
        }


    }

    void ParentJsonObjListadd(JSONObject JSOBJ,String listKey,List<Object> JsList){

        JSOBJ.put(listKey,JsList);

    }

}
