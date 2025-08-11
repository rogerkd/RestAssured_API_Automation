package com.yourorg.tests;

import com.aventstack.extentreports.Status;
import com.yourorg.base.TestListener;
import org.testng.annotations.Listeners;
import com.yourorg.config.ConfigReader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Listeners(TestListener.class)
public class NegativeAuthTest {

    @Test
    public void getAccessToken_ShouldFail_WhenInvalidCredentials() {
        ConfigReader cfg = ConfigReader.getInstance();

        Response res = given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("grant_type", "client_credentials")
            .formParam("client_id", "wrong-id")
            .formParam("client_secret", "wrong-secret")
        .when()
            .post(cfg.get("auth.token_url"));

        TestListener.test.get().log(Status.INFO, "Response: " + res.asPrettyString());

        res.then()
            .statusCode(401)
            .body("error", anyOf(containsString("unauthorized"), containsString("invalid_client")));
    }
}
