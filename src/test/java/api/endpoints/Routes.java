package api.endpoints;

import io.restassured.RestAssured;

public class Routes {
    // user module

    public static String baseUrl = "https://petstore.swagger.io/v2";

     public static String postUrl = baseUrl+ "/user/";
     public static String getUrl = baseUrl+"/user/{username}";
     public static String updateUrl = baseUrl+"/user/{username}";
    public static String deleteUrl = baseUrl+"/user/{username}";

}