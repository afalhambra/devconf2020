package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@QuarkusTest
public class ResourcesTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello?name=Joe")
          .then()
             .statusCode(200)
             .body(containsString("Hello Joe!"));
    }

    @Test
    public void testRandomEndpoint() {
        given()
          .when().get("/random")
          .then()
             .statusCode(200)
             .body(Matchers.matchesRegex("[0-9]|[1-9][0-9]|100"));
    }

    @Test
    public void testDateEndpoint() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        given()
          .when().get("/date")
          .then()
             .statusCode(200)
             .body(containsString(dtf.format(now).substring(0, 11)));
    }

}