package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {
    // it contains CRUD method implementation

   public static Response createUser(User payload){
        Response response=  given()
                 .contentType(ContentType.JSON)
             //  .accept(ContentType.JSON)
               .body(payload)
         .when()
                 .post(Routes.postUrl);
         return response;
     }


    public static Response readUser(String userName){
        Response response=  given()
                .pathParam("username", "userName")
                .when()
                .get(Routes.getUrl);
        return response;
    }

    public static Response updateUser(String userName , User payload){
        Response response=  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username", "userName")
                .when()
                .put(Routes.updateUrl);
        return response;
    }

    public static Response deleteUser(String userName ,User payload){
        Response response=  given()
                .pathParam("username", "userName")
                .when()
                .delete(Routes.deleteUrl);
        return response;
    }

}