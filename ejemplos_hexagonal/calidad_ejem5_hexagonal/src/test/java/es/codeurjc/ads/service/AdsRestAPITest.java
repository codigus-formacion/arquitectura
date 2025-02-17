package es.codeurjc.ads.service;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.codeurjc.ads.Application;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
public class AdsRestAPITest {

    @Test
    public void givenDemoData_whenAdIsGet_thenIsCorrect() throws ForbiddenWordsException {

        when()
            .get("/ads/{id}", 1)
        .then()
            .statusCode(200)
            .body(
                "name", equalTo("Peter"), 
                "title", equalTo("I sell my car"));
    }

    @Test
    public void givenValidAd_whenIsPosted_thenCanBeRetrieved() throws ForbiddenWordsException {

        Response response = given()
            .body("{\"name\":\"author\", \"title\": \"title\", \"comment\":\"comment\"}")
            .contentType(ContentType.JSON)
        .when()
            .post("/ads/")
        .thenReturn();

        assertThat(response.getStatusCode(), equalTo(200));
        
        Integer id = from(response.getBody().asString()).get("id");

        when()
            .get("/ads/{id}", id)
        .then()
            .statusCode(200)
            .body(
                "name", equalTo("author"), 
                "title", equalTo("title"));
    }

    @Test
    public void givenInvalidAd_whenIsPosted_thenBadRequest() throws ForbiddenWordsException {

        given()
            .body("{\"name\":\"author\", \"title\": \"title violence\", \"comment\":\"comment\"}")
            .contentType(ContentType.JSON)
        .when()
            .post("/ads/")
        .then()
            .statusCode(400);
    }

}
