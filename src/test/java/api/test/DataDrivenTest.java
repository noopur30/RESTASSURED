package api.test;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Endpoints.UserEndPoints;
import api.Payloads.User_Pojo;
import api.Utilities.Dataproviders;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class DataDrivenTest {
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=Dataproviders.class)
	
	public void testpostuser(String userid,String username,String firstname,String lastname,String emailid,String password,String phone) {
		
		User_Pojo userpayload = new User_Pojo();
		
		userpayload.setId(Integer.parseInt(userid));
		userpayload.setUsername(username);
		userpayload.setFirstname(firstname);
		userpayload.setLastname(lastname);
		userpayload.setEmail(emailid);
		userpayload.setPassword(password);
		userpayload.setPhone(phone);
		
	Response res=	UserEndPoints.createuser(userpayload);
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProvider.class)
	public void testdeleteuserbyname(String username) {
		Response res=UserEndPoints.deleteuser(username);
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
