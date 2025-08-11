package com.yourorg.base;

import com.yourorg.config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    protected static RequestSpecification baseSpec;
    protected static ConfigReader cfg = ConfigReader.getInstance();

    @BeforeSuite
    public void setup() {
        RequestSpecBuilder rb = new RequestSpecBuilder()
                .setBaseUri(cfg.get("base.uri"))
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json");
        baseSpec = rb.build();
    }
}
