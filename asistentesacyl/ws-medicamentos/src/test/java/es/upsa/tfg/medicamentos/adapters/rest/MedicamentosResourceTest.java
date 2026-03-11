package es.upsa.tfg.medicamentos.adapters.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class MedicamentosResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/medicamentos")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

}