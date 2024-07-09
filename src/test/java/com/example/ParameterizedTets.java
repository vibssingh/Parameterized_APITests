package com.example;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(DataProviderRunner.class)
public class ParameterizedTets {

    @DataProvider
    public static Object[][] responseData() {
        return new Object[][] {
                { "1", "George", "Bluth" },
                { "2", "Janet", "Weaver" },
                { "3", "Emma", "Wong" },
                { "4", "Eve", "Holt" }
        };
    }

    @Test
    @UseDataProvider("responseData")
    public void verifyResponse(String id, String expectedFirstName, String expectedLastName)
    {
        given().
                when().
                pathParam("id", id).
                get("https://reqres.in/api/users/{id}").
                then().
                assertThat().
                statusCode(200).
                body("data.first_name", equalTo(expectedFirstName)).
                body("data.last_name", equalTo(expectedLastName));
    }
}
