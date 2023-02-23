package com.lekshmi.learningRestAPITest;
import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lekshmi.learningRestAPI.Base.TestBase;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class ProductAPI extends TestBase {
	int productId;
	@BeforeMethod
	public void setup() {
		launchUrl();
	}
	
	@Test(priority=0)
	public void getProducts() {
		Response response=given().when().get("/products");
		productId= response.jsonPath().get("data[2].id");
		response.then().statusCode(200);
	}
	
	@Test(priority=1)
	public void getProductById() {	
		Response response= given().pathParam("id",productId).log().all()
				.when().get("/products/{id}");
		response.then().assertThat().statusCode(200);
		
	}
	
	
	@Test(priority=2)
	public void deleteProductById() {	
		Response response= given().pathParam("id",productId).log().all()
				.when().delete("/products/{id}");
		
		response.then().statusCode(200).log().all();

	}

}
