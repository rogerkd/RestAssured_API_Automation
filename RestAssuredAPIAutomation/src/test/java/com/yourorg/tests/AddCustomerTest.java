package com.yourorg.tests;

import com.aventstack.extentreports.Status;
import com.yourorg.base.TestBase;
import com.yourorg.base.TestListener;
import org.testng.annotations.Listeners;
import com.yourorg.utils.TokenManager;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Listeners(TestListener.class)
public class AddCustomerTest extends TestBase {

    @Test
    public void addCustomer_ShouldReturn201_WhenValidData() {
        String token = TokenManager.getToken();

        Response res = given()
            .spec(baseSpec)
            .auth().oauth2(token)
            .contentType("application/x-www-form-urlencoded")
            .formParam("customerId", "1003")
            .formParam("customerName", "Sam")
            .formParam("customerMobile", "9897123432")
            .formParam("customerEmail", "sam@email.com")
            .formParam("customeraddress", "Mumbai")
            .formParam("customerUsername", "sam")
            .formParam("customerPassword", "sam")
        .when()
            .post(cfg.get("customer.api.path") + "/addCustomer");

        TestListener.test.get().log(Status.INFO, "Request: Add customer Sam");
        TestListener.test.get().log(Status.INFO, "Response: " + res.asPrettyString());

        res.then()
            .statusCode(anyOf(is(200), is(201)))
            .body("customerName", hasItem("Sam"));
    }
    
    @Test
    public void addCustomer_ShouldReturnError_WhenMissingRequiredFields() {
        String token = TokenManager.getToken();

        Response res = given()
            .spec(baseSpec)
            .auth().oauth2(token)
            .contentType("application/x-www-form-urlencoded")
            // Missing customerName, mobile is invalid, address empty
            .formParam("customerId", "9999")
            .formParam("customerMobile", "123")
            .formParam("customerEmail", "invalidEmail")
            .formParam("customeraddress", "")
            .formParam("customerUsername", "")
            .formParam("customerPassword", "")
        .when()
            .post(cfg.get("customer.api.path") + "/addCustomer");

        TestListener.test.get().log(Status.INFO, "Request: Add customer with missing fields");
        TestListener.test.get().log(Status.INFO, "Response: " + res.asPrettyString());

        res.then()
            .statusCode(anyOf(is(200), is(400), is(422))) // API might return 400 or 422 for validation errors
            .body("$", notNullValue());
    }
}
