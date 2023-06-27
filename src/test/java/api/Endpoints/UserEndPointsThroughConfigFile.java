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

import java.util.ResourceBundle;

public class UserEndPointsThroughConfigFile {
	
  public static	ResourceBundle getUrls(){
		ResourceBundle routes = ResourceBundle.getBundle("Routes");
		return routes;
	}
	

	public static Response createuser(User_Pojo payload){
		
		String post_url=getUrls().getString("post_url");
		
	Response response =	given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
		.when().post(post_url);
         return response;
	}
public static Response readuser(String username){
		String geturl = getUrls().getString("get_url");
		Response response =	given().pathParam("username", username)
			.when().get(geturl);
		
		return response;

		}
public static Response updateuser(User_Pojo payload,String username){
	String updateurl=getUrls().getString("update_url");
	Response response =	given().contentType(ContentType.JSON).pathParam("username", username).accept(ContentType.JSON).body(payload)
		.when().put(updateurl);
	
	return response;

	}
public static Response deleteuser(String username){
	String deleteurl= getUrls().getString("delete_url");
	 Response response=given().pathParam("username", username)
	 .when().delete(deleteurl);
	 return response;
}




}
