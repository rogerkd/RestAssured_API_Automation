package com.yourorg.tests;

import com.aventstack.extentreports.Status;
import com.yourorg.base.TestBase;
import com.yourorg.base.TestListener;
import org.testng.annotations.Listeners;
import com.yourorg.utils.TokenManager;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Listeners(TestListener.class)
public class AuthTest extends TestBase {

    @Test
    public void getAccessToken_ShouldReturnValidToken() {
        String token = TokenManager.getToken();

        TestListener.test.get().log(Status.INFO, "Generated Token: " + token);

        assertThat("Access token should not be null or empty", token, not(emptyOrNullString()));
    }
}
