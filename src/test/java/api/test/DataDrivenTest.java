package api.test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilites.dataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest {

@Test(priority = 1,dataProvider = "Data", dataProviderClass = dataProvider.class)
    public void testPostUser(String userID,String userName, String fname ,String lname,String userEmail , String pass ,String ph){


       User userPayload = new User();
         userPayload.setId(Integer.parseInt(userID));
         userPayload.setUsername(userName);
         userPayload.setFirstName(fname);
         userPayload.setLastName(lname);
         userPayload.setEmail(userEmail);
         userPayload.setPassword(pass);
         userPayload.setPhone(ph);

         Response response = UserEndPoints.createUser(userPayload);
         response.then().log().all();

    Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2 , dataProvider = "UserNames",dataProviderClass = dataProvider.class)
    public void testDeleteUserByName(String userName){

       Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);

    }

}
