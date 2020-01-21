package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;
import io.restassured.RestAssured;

public class ResourceInterceptionTest {

    @RegisterExtension
    static QuarkusUnitTest test = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class).addClasses(TestResource.class, EventsCollector.class)
                    .addAsResource(new StringAsset("quarkus.watcher.limit=100"), "application.properties"));

    @Inject
    EventsCollector collector;

    @Test
    public void testResourceGetsIntercepted() {
        RestAssured.given()
                .when().get("/test")
                .then()
                .statusCode(200)
                .body(CoreMatchers.containsString("ping"));

        RestAssured.given()
                .when().get("/test/limit")
                .then()
                .statusCode(200)
                .body(CoreMatchers.containsString("ping"));

        assertEquals(1, collector.getEvents().size());
        assertEquals("org.acme.TestResource#limitExceeded()", collector.getEvents().get(0).methodInfo);
    }

}
