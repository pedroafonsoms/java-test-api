package test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.Config;

import static io.restassured.RestAssured.get;

public class UserContractTest {

    @BeforeClass
    public static void executarAntesDeTodosTestes(){
        Config.setBaseUrl("users");
    }

    @Test
    public void deveListarOsUsuarios() {
        get().then()
            .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("users_schema.json"));
    }
}
