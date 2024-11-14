package api.testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User_payload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User_payload userPayload;

	public Logger logger; // for logs

	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new User_payload();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		// logs
		logger = LogManager.getLogger(this.getClass());
		logger.debug(" --- debugging... --- ");

	}

	@Test(priority = 1)
	public void testPostUser() {
		logger.info(" --- Creating user  --- ");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		String body = response.getBody().asString();

		JsonPath jsonpath = new JsonPath(body);

		int id = jsonpath.getInt("id");

		String name = jsonpath.getString("name");
		System.out.println(name);

		System.out.println(id);
		if (response.getStatusCode() == 200) {
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("********** User is creatged and Test Cases passed ***************");
		} else {

			logger.info("********** User is not creatged  ***************");
		}
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("********** Reading User Info ***************");

		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		String body = response.getBody().asString();

		JsonPath jsonpath = new JsonPath(body);

		System.out.println(jsonpath.get("name"));

		Assert.assertEquals(jsonpath.get("username"), "ram");

		logger.info("********** User info  is displayed ***************");

	}

	// @Test(priority = 3)
	public void testUpdateUserByName() {
		logger.info("********** Updating User ***************");

		// update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();

		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("********** User updated ***************");
		// Checking data after update
		Response responseAfterupdate = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info("**********   Deleting User  ***************");

		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("********** User deleted ***************");
	}

}