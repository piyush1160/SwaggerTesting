package api.test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class UserTest {

    // faker is a class in which we are generating fake number , fake id means randomly generate
    Faker faker;
    User userPayload;
    JsonPath json;
    String jsonresponse;
    String uname = "query";
    String upass = "query";

    String sendingusername ="";

    @BeforeClass
    public void setUpData() {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        sendingusername = this.userPayload.getUsername();

//        userPayload.setId(1235550);
//        userPayload.setPhone("123456780");
//        userPayload.setPassword("Test@123");
//        userPayload.setEmail("asdfghj@fghjk.com");
//        userPayload.setLastName("preet");
//        userPayload.setFirstName("Aman");
//        userPayload.setUserStatus(12);
//        userPayload.setUsername("testing");

    }

//   @Test
//    public void getTest(){
//        RestAssured.baseURI= "https://reqres.in/api";
//        given().get("/users?page=2")
//                .then()
//                .assertThat()
//                .body(matchesJsonSchemaInClasspath("schema.json"))
//                .statusCode(200);
//    }


    @Test(priority = 1)
    public void loginUser() {
        Response response = UserEndPoints.LoginUser(this.userPayload.getUsername(), this.userPayload.getPassword());
        //Response response = UserEndPoints.LoginUser(uname,upass);
        response.then().log().all();
      //  username = this.userPayload.getUsername();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testPostUser() {
        Response response = UserEndPoints.createUser(userPayload);

        //validate response ..
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"));
        //String res =
//      String responseString =  response.then().log().all().extract().asString();
//            System.out.println(responseString);
//    JsonPath json = new JsonPath(responseString);
//       String type = json.getString("type");
//
//       // validate response body..
//       Assert.assertEquals(type , "unknown" );
//        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void validateReqJSONBody(){
        String strBody = UserEndPoints.userBody(this.userPayload.getUsername());

        System.out.println(strBody);

         JsonPath  json = new JsonPath(strBody);
         String expectedusername = json.getString("username");
         String expectedfirstName= json.getString("firstName");
         String expectedLastName = json.getString("lastName");
         int id = json.getInt("id");
         String expectedemail = json.getString("email");
        String expectedpass =   json.getString("password");

        Assert.assertEquals(expectedusername,sendingusername,"username");
         Assert.assertEquals(expectedfirstName,this.userPayload.getFirstName() , "firstname validate ");
        Assert.assertEquals(expectedLastName,this.userPayload.getLastName() , "lastname validate ");
        Assert.assertEquals(id,  this.userPayload.getId() , "id validate ");
        Assert.assertEquals(expectedemail,  this.userPayload.getEmail() , "email validate ");
        Assert.assertEquals(expectedpass,  this.userPayload.getPassword() , "password validate ");

    }

//    {
//        "id": 1427651360,
//            "username": "jamal.barton",
//            "firstName": "Ned",
//            "lastName": "Stiedemann",
//            "email": "margarito.pagac@example.com",
//            "password": "e7zsxzs0",
//            "phone": "686-539-0966",
//            "userStatus": 0
//    }



    @Test(priority = 4)
    public void testGetUserByName() {
        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

    }
//    @Test(priority = 5)
//    public void testGetUserById() {
//        Response response = UserEndPoints.readUser(this.userPayload.getId());
//        response.then().log().all();
//
//        Assert.assertEquals(response.getStatusCode(), 200);
//
//    }

    @Test(priority = 6)
    public void updateUserByName() {
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());


        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        // after updation
        Response responseAfterUpdate = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    //
    @Test(priority = 7)
    public void deleteUserByName() {

        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        // response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }

//
//        Response response  =  UserEndPoints.deleteUser(this.userPayload.getUsername());
//       // response.then().log().all();
//        Assert.assertEquals(response.getStatusCode(),200);
//    }


    @Test(priority = 8)
    public void logout() {

        Response response = UserEndPoints.logoutUser(this.userPayload.getUsername(), this.userPayload.getPassword());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);


    }
}


