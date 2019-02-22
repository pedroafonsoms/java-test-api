package test;

import com.google.gson.JsonObject;
import org.hamcrest.collection.IsArray;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.Config;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserIntegrationTest {

    @BeforeClass
    public static void executarAntesDeTodosTestes(){
        Config.setBaseUrl("users");
    }

    @Test
    public void deveListarOsUsuarios() {
        get().then()
            .assertThat()
                .statusCode(200)
                .body("data", hasSize(3),
                "data", not(emptyArray()));
    }

    @Test
    public void deveListarQuantidadeSolicitadaDeUsuarios(){
        given().param("per_page", 10).
        get().then()
            .assertThat()
                .statusCode(200)
                .body("per_page", equalTo(10),
                "data", hasSize(10),
                         "data", not(emptyArray()));
    }

    @Test
    public void deveListarApenasUmUsuario() {
        get("2").then()
            .assertThat()
                .statusCode(200)
                .body("data", not(IsArray.array()),
                "data.first_name", equalTo("Janet"));
    }

    @Test
    public void naoDeveEncontrarNenhumUsuario(){
        get("100").then()
            .assertThat()
                .statusCode(404);
    }

    @Test
    public void cadastrarUmUsuarioComSucesso(){
        JsonObject user = new JsonObject();
        user.addProperty("name", "Darth Vader");
        user.addProperty("job", "Leader Galaxy");

        with().body(user.toString()).
        post().then()
            .assertThat()
                .statusCode(201);
    }
}
