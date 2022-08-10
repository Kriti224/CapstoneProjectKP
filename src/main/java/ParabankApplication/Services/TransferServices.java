package ParabankApplication.Services;

import ParabankApplication.Models.Credentials;
import ParabankApplication.Models.TransferRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class TransferServices {

    public static String baseUrl = "https://parabank.parasoft.com/parabank/services_proxy/bank";

    public static ObjectMapper mapper=new ObjectMapper();

    public static Response transferAmount(Credentials credentials, TransferRequest transferRequest){

        Map<String,Object> params = mapper.convertValue(transferRequest,Map.class);

        var response = given()
                .auth().basic(credentials.username, credentials.password)
                .params(params)
                .post(baseUrl+"/transfer");

        return response;
    }
}
