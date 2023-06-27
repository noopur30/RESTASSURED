package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Endpoints.UserEndPoints;
import api.Endpoints.UserEndPointsThroughConfigFile;
import api.Payloads.User_Pojo;
import io.restassured.response.Response;

public class UserTestThroughPropertiesFile {
	
	
	Faker faker;
	User_Pojo userpayload;
	public Logger logger;
	@BeforeClass
	public void setupData() {
		faker=new Faker();
	 userpayload = new User_Pojo();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testpostuser() {
		
		logger.info("loding user payload");
		Response response=UserEndPointsThroughConfigFile.createuser(userpayload);
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("User Created");//Similarly log messages can be added to other methods as well
		
	}
	
	@Test(priority=2)
	public void testgetuser() {
		Response response=UserEndPointsThroughConfigFile.readuser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=3)
	public void testupdateuser() {
		//update user payload
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		Response response=UserEndPointsThroughConfigFile.updateuser(userpayload, this.userpayload.getUsername());
		
		response.then().log().body().statusCode(200);
		Response responseupdated =UserEndPointsThroughConfigFile.readuser(this.userpayload.getUsername());
		responseupdated.then().log().all();
		responseupdated.then().log().body().statusCode(200);
		String string=responseupdated.jsonPath().get("email").toString();
		Assert.assertEquals(string, userpayload.getEmail());
		System.out.println(string);
	}


}
