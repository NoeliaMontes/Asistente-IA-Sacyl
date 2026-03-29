package es.upsa.tfg.aggregator.adapters.input.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class AggregatorResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/aggregator")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

}