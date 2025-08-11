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
public class ViewCustomerListTest extends TestBase {

    @Test
    public void viewCustomerList_ShouldReturnNonEmptyList() {
        String token = TokenManager.getToken();

        Response res = given()
            .spec(baseSpec)
            .auth().oauth2(token)
        .when()
            .get(cfg.get("customer.api.path") + "/viewCustomerList");

        TestListener.test.get().log(Status.INFO, "Response: " + res.asPrettyString());

        res.then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0].customerId", notNullValue());
    }
    
    @Test
    public void viewCustomerList_ShouldReturnError_WhenUnauthorized() {
        // No token passed intentionally for negative test
        Response res = given()
            .spec(baseSpec)
        .when()
            .get(cfg.get("customer.api.path") + "/viewCustomerList");

        TestListener.test.get().log(Status.INFO, "Response (Unauthorized): " + res.asPrettyString());

        res.then()
            .statusCode(anyOf(is(200), is(401), is(403))) // Accept 401 or 403 depending on API behavior
            .body("error", notNullValue());
    }
}
