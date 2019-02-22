package utils;

import io.restassured.RestAssured;

public class Config {

    public static void setBaseUrl(String pathUri){
        RestAssured.baseURI = "https://reqres.in/api/";
        RestAssured.baseURI += pathUri;
    }

}
