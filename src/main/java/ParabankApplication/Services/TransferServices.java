package ParabankApplication.Services;

import ParabankApplication.Models.Credentials;
import ParabankApplication.Models.TransferRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class TransferServices {

    public String baseUrl = "https://parabank.parasoft.com/parabank/services_proxy/bank";

    public ObjectMapper mapper=new ObjectMapper();

    public void transferAmount(Credentials credentials, TransferRequest transferRequest){

        Map<String,Object> params = mapper.convertValue(transferRequest,Map.class);

        var response = given()
                .auth().basic(credentials.username, credentials.password)
                .params(params)
                .post(baseUrl+"/transfer")
                .then().statusCode(200);

        var responseBody = response.extract().body().asString();
    }
}
