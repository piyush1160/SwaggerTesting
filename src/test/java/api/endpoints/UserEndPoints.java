package api.endpoints;

import api.payloads.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserEndPoints {
    // it contains CRUD method implementation

   public static Response createUser(User payload){
        Response response=  given()
                 .contentType(ContentType.JSON)
             .accept(ContentType.JSON)
               .body(payload)
         .when()
                 .post(Routes.postUrl);
         return response;
     }


     public static String userBody(String username){
        String stringBody = given()
                .pathParam("username", username)
                 .when()
                .get(Routes.getUrl)
                .then().log().all().extract().body().asString();
        return stringBody;
     }




    public static Response readUser(String username){
        Response response=  given()
                .pathParam("username", username)
                .when()
                .get(Routes.getUrl);
        return response;
    }

    public static Response readUser2(String username,User payload){
        Response response=  given()
                .pathParam("username", username).body(payload)
                .when()
                .get(Routes.getUrl);
        return response;
    }

    public static Response readUser(int id){
        Response response=  given()
                .pathParam("id", id)
                .when()
                .get(Routes.getUrlId);
        return response;
    }


    public static Response updateUser(String userName , User payload){
        Response response=  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username", userName)
                .when()
                .put(Routes.updateUrl);
        return response;
    }

    public static Response deleteUser(String userName ){
        Response response=  given()
                .pathParam("username", userName)
                .when()
                .delete(Routes.deleteUrl);
        return response;
    }

    public static Response LoginUser(String username,String password){
       Response response = given()
               .queryParam("username",username)
               .queryParam("password",password)
               .when()
               .get(Routes.loginUrl);
        return response;
    }
    public static Response logoutUser(String username , String password){
//       Response response = given().queryParam("username",username)
//                .queryParam("password",password)
        Response response = given().when()
                .get(Routes.logoutUrl);
        return response;
    }

}
