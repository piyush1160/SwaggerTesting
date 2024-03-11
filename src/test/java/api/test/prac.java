package api.test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class prac {

@Test
    public void getTest(){
        RestAssured.baseURI= "https://reqres.in/api";
        given().get("/users?page=2")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema.json"))
                .statusCode(200);
    }

}
