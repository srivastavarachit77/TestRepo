package RestAssuredAPI.RestAssuredAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.data;

public class GoogleMapAPI 
{
    public static void main( String[] args )
    {
    	// Given - Query Param, Header, Body
    	// When - RequestType, Resource
    	// Then - Assertions, Response, Validations
        RestAssured.baseURI="https://rahulshettyacademy.com";
        // Post Request and then stored response in a String variable response
        // Use relaxed HTTP validation with SSLContext protocol SSL.
        String postResponse = given().relaxedHTTPSValidation().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
        .body(data.AddPlaceBody())
        .when().relaxedHTTPSValidation().log().all().post("/maps/api/place/add/json")
        .then().log().all().statusCode(200).assertThat().body("status", equalTo("OK")).extract().response().asString();
        System.out.println("=============response========================");
       // Parsing Post response via JsonPath      
       JsonPath postJS=new JsonPath(postResponse);
       String placeID=postJS.getString("place_id");
       System.out.println("<======Post Executed Successfully======>");
       
        //===============Update Address=================
        String NewAddress="SPET26_NewAddress";
        given().relaxedHTTPSValidation().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
        .header("Content-Type","application/json")
        .body("{\r\n" + 
				"\"place_id\":\""+placeID+"\",\r\n" + 
				"\"address\":\""+NewAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
        .when().relaxedHTTPSValidation().log().all().put("/maps/api/place/update/json")
        .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
        
      
       
     //Get Request and then stored response in a String variable response
       String getResponse=given().relaxedHTTPSValidation().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
        .when().relaxedHTTPSValidation().get("/maps/api/place/get/json")
       .then().assertThat().log().all().statusCode(200).body("phone_number", equalTo("(+91) 8888880925")).extract().response().asString();
       //Parsing Get response
      JsonPath getJS=new JsonPath(getResponse);
      // JsonPath getJS=data.rawToJson(getResponse);
       String Name=getJS.getString("name");
       String Phone=getJS.getString("phone_number");
       String Address=getJS.getString("address");
       System.out.println("Get Response is=> Name:"+Name+"Phone:  "+Phone+"Address: "+Address);
       Assert.assertEquals(Name, "SEPT25_Name");
       Assert.assertEquals(Phone, "(+91) 8888880925");
       Assert.assertEquals(Address, NewAddress);
       System.out.println("<======Get Executed Successfully======>");
      
       
       
        
    }
}
