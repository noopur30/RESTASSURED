package api.Endpoints;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Payloads.User_Pojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
public class UserEndPoints {
	
	public static Response createuser(User_Pojo payload){
		
	Response response =	given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
		.when().post(Routes.post_url);
           return response;
	}
	
	public static Response readuser(String username){
		
		Response response =	given().pathParam("username", username)
			.when().get(Routes.get_url);
		
		return response;

		}
	
public static Response updateuser(User_Pojo payload,String username){
		
		Response response =	given().contentType(ContentType.JSON).pathParam("username", username).accept(ContentType.JSON).body(payload)
			.when().put(Routes.update_url);
		
		return response;

		}

   public static Response deleteuser(String username){
    	 Response response=given().pathParam("username", username)
    	 .when().delete(Routes.delete_url);
    	 return response;
     }
	

}
