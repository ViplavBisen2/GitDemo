package day2;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
public class NewReqBodyPOST {
	String userid;
	
	//1.Using HashMap
	//@Test(priority=1)
	public void testPostUsingHashMap() {
		HashMap data =new HashMap();
		
		data.put("name", "Scott");
		data.put("location","Italy");
		data.put("phone","95454522");
		
		//create java array for courses
		
		String CourseArray[] = {"Ruby","Cotlin"};
		data.put("courses", CourseArray);
		
		
		//Post this data
		given().contentType("application/json").body(data).
		when().post("http://localhost:3000/students")
		.then().statusCode(201).body("name",equalTo("Scott")).body("location",equalTo("Italy")).
		body("courses[1]",equalTo("Cotlin")).header("Content-Type","application/json")
		.log().all();
		
	}
	//2.Using ORG.JSOn LIbrary
		//@Test(priority=1)
		public void testPostUsingJsonLibrary() {
			//Create JSONObject
			
			JSONObject data = new JSONObject();
			
			data.put("name", "Scott");
			data.put("location","France");
			data.put("phone","95454522");
			
			String CourseArray[] = {"Ruby","Cotlin"};
			data.put("courses", CourseArray);
			
			//Post this data
			//To send JsonObject data it must be in string format.
			given().contentType("application/json").body(data.toString()).
			when().post("http://localhost:3000/students")
			.then().statusCode(201).body("name",equalTo("Scott")).body("location",equalTo("France")).
			body("courses[1]",equalTo("Cotlin")).header("Content-Type","application/json")
			.log().all();
			
		}
		
		//3.Using POJO Class
				@Test(priority=1)
				public void testPostUsingPOJOClass() {
					PojoPostRequest data=new PojoPostRequest();
					data.setName("Scott");
					data.setLocation("China");
					data.setPhone("7964654");
					
					String courseArray[] = {"C","C++"};
					data.setCourses(courseArray);
					
					//Post this data
					//To send JsonObject data it must be in string format.
				String response=given().contentType("application/json").body(data).
					when().post("http://localhost:3000/students").asString()
					;
				JsonPath js =new JsonPath(response);
				userid= js.get("id");
				System.out.println(userid);
				
					
				}
	//Delete this request
	@Test(priority=2)
	void deletetest() {
		given().when().delete("http://localhost:3000/students/"+userid).then().statusCode(200);
		
	}
	
}
