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
public class ViewCustomerByMobileTest extends TestBase {

    @Test
    public void viewCustomerByMobile_ShouldReturnCustomer_WhenValidMobile() {
        String token = TokenManager.getToken();

        Response res = given()
            .spec(baseSpec)
            .auth().oauth2(token)
            .pathParam("customerMobile", "1234567890")
        .when()
            .get(cfg.get("customer.api.path") + "/viewCustomerByMobile/{customerMobile}");

        TestListener.test.get().log(Status.INFO, "Response: " + res.asPrettyString());

        res.then()
            .statusCode(200)
            .body("customerMobile", equalTo("1234567890"));
    }

    @Test
    public void viewCustomerByMobile_ShouldReturnError_WhenInvalidMobile() {
        String token = TokenManager.getToken();

        Response res = given()
            .spec(baseSpec)
            .auth().oauth2(token)
            .pathParam("customerMobile", "9999999999")
        .when()
            .get(cfg.get("customer.api.path") + "/viewCustomerByMobile/{customerMobile}");

        TestListener.test.get().log(Status.INFO, "Response: " + res.asPrettyString());

        res.then()
            .statusCode(anyOf(is(204), is(404), is(400)));
    }
}
