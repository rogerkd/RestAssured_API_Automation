package com.yourorg.utils;

import com.yourorg.config.ConfigReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String token;
    private static long expiryTime;

    public static String getToken() {
        if (token == null || System.currentTimeMillis() >= expiryTime) {
            ConfigReader cfg = ConfigReader.getInstance();
            Response res = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", cfg.get("auth.client_id"))
                .formParam("client_secret", cfg.get("auth.client_secret"))
            .when()
                .post(cfg.get("auth.token_url"))
            .then()
                .statusCode(200)
                .extract().response();

            token = res.jsonPath().getString("access_token");
            int expiresIn = res.jsonPath().getInt("expires_in");
            expiryTime = System.currentTimeMillis() + (expiresIn - 30) * 1000L;
        }
        return token;
    }
}
