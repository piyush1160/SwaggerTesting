package api.test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    // faker is a class in which we are generating fake number , fake id means randomly generate
    Faker faker;
    User userPayload;
    JsonPath json ;
     String jsonresponse;
     String uname= "query";
     String upass ="query";

    @BeforeClass
    public void setUpData(){
       faker = new Faker();
       userPayload = new User();

       userPayload.setId(faker.idNumber().hashCode());
       userPayload.setUsername(faker.name().username());
       userPayload.setFirstName(faker.name().firstName());
       userPayload.setLastName(faker.name().lastName());
       userPayload.setEmail(faker.internet().safeEmailAddress());
       userPayload.setPassword(faker.internet().password(5,10));
       userPayload.setPhone(faker.phoneNumber().cellPhone());
//        userPayload.setId(1235550);
//        userPayload.setPhone("123456780");
//        userPayload.setPassword("Test@123");
//        userPayload.setEmail("asdfghj@fghjk.com");
//        userPayload.setLastName("preet");
//        userPayload.setFirstName("Aman");
//        userPayload.setUserStatus(12);
//        userPayload.setUsername("testing");

    }

    @Test(priority = 1)
    public void loginUser (){
        Response response = UserEndPoints.LoginUser(this.userPayload.getUsername(),this.userPayload.getPassword());
        //Response response = UserEndPoints.LoginUser(uname,upass);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void testPostUser()
    {
       Response response=    UserEndPoints.createUser(userPayload);
       //String res =
               response.then().log().all();
    //    System.out.println(res);
        Assert.assertEquals(response.getStatusCode(),200);
    }

   @Test(priority = 3)
    public void testGetUserByName(){
       Response response =  UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 4)
    public void updateUserByName(){
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());


        Response response=    UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
// after updation
        Response responseAfterUpdate=    UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);

    }
//
    @Test(priority = 5)
    public void deleteUserByName(){

        Response response  =  UserEndPoints.deleteUser(this.userPayload.getUsername());
       // response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }


    @Test(priority = 6)
    public void logout(){

        Response response = UserEndPoints.logoutUser(this.userPayload.getUsername(),this.userPayload.getPassword());
         response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);


    }

}
