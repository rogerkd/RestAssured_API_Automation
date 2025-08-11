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
public class ViewCustomerByIdTest extends TestBase {

    @Test
    public void viewCustomerById_ShouldReturnCustomer_WhenValidId() {
        String token = TokenManager.getToken();
        String validId = "1001";

        Response res = given()
            .spec(baseSpec)
            .auth().oauth2(token)
            .pathParam("customerId", validId)
        .when()
            .get(cfg.get("customer.api.path") + "/viewCustomerById/{customerId}");

        TestListener.test.get().log(Status.INFO, "Response: " + res.asPrettyString());

        res.then()
            .statusCode(200)
            .body("customerId.toString()", equalTo(validId));
    }

    @Test
    public void viewCustomerById_ShouldReturnError_WhenInvalidId() {
        String token = TokenManager.getToken();

        Response res = given()
            .spec(baseSpec)
            .auth().oauth2(token)
            .pathParam("customerId", "9999")
        .when()
            .get(cfg.get("customer.api.path") + "/viewCustomerById/{customerId}");

        TestListener.test.get().log(Status.INFO, "Response: " + res.asPrettyString());

        res.then()
            .statusCode(anyOf(is(204), is(404), is(400)));
    }
}
