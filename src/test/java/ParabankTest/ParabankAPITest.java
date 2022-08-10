package ParabankTest;

import ParabankApplication.Models.Credentials;
import ParabankApplication.Models.TransferRequest;
import ParabankApplication.Services.TransferServices;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParabankAPITest {

    @Test
    public void transferFundsViaAPITest1(){
        Credentials credentials=new Credentials();
        TransferRequest transferRequest=new TransferRequest(12345,12456,100);
        TransferServices transferServices=new TransferServices();
        var response = transferServices.transferAmount(credentials,transferRequest)
                .then().statusCode(200)
                .extract().response();

        String responseMessage = response.getBody().asString();
        Assert.assertEquals(responseMessage,"Successfully transferred $"+transferRequest.amount+" from account #"+ transferRequest.fromAccountId+" to account #"+transferRequest.toAccountId);
    }

    @Test
    public void transferFundsViaAPITest2(){
        Credentials credentials=new Credentials();
        TransferRequest transferRequest=new TransferRequest(12456,12567,500);
        TransferServices transferServices=new TransferServices();
        var response = transferServices.transferAmount(credentials,transferRequest)
                .then().statusCode(200)
                .extract().response();

        String responseMessage = response.getBody().asString();
        Assert.assertEquals(responseMessage,"Successfully transferred $"+transferRequest.amount+" from account #"+ transferRequest.fromAccountId+" to account #"+transferRequest.toAccountId);
    }

    @Test
    public void transferFundsViaAPITest3(){
        Credentials credentials=new Credentials();
        TransferRequest transferRequest=new TransferRequest(12345,12456,1000000);
        TransferServices transferServices=new TransferServices();
        var response = transferServices.transferAmount(credentials,transferRequest)
                .then().statusCode(200)
                .extract().response();

        String responseMessage = response.getBody().asString();
        Assert.assertEquals(responseMessage,"Successfully transferred $"+transferRequest.amount+" from account #"+ transferRequest.fromAccountId+" to account #"+transferRequest.toAccountId);
    }
}
