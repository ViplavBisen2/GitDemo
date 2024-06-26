package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HttpRequests {

	int id;

	 @Test (priority =1)
	void getUsers() {
		// multiple users
		given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2)).log()
				.all();

	}

	@Test(priority = 2)
	void createUser() {
		// Create hashmap to store input

		HashMap hm = new HashMap();

		hm.put("name", "Alan");
		hm.put("job", "Trainer");

		id = given().contentType("application/json").body(hm).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");
		// No need to use then to validate
		// .then();statusCode(201).log().all();

	}

	// USing this ID get the user
	@Test(priority = 3 ,dependsOnMethods = {"createUser"})
	void updateUser() {

		HashMap hm = new HashMap();

		hm.put("name", "Alan");
		hm.put("job", "Trainer");

		given().contentType("application/json").body(hm).when().put("https://reqres.in/api/users/" + id).then()
				.statusCode(200).log().all();
		;

	}
	
	
	@Test(priority=4)
	void deleteUser() {
		
		given().contentType("application/json").when().delete("https://reqres.in/api/users/" + id).then()
		.statusCode(204).log().all();
		
		
		
		
		
		
		
	}

}
