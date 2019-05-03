package com.brumax.tp.userservice;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRessources {

    @LocalServerPort
    private int port;

    @Before
    public void init(){
        RestAssured.port = port;
    }

    @Test
    public void full_senario(){

        CreatUserDto dto = new CreatUserDto();
        dto.setFirstname("toto");
        dto.setLastname("tata");

        String location =
                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        //.body("{ \"firstname\" : \"toto\", \"lastname\":\"tata\"}")
                        .body(dto)
                .when()
                        .post("/users/")
                .then()
                        .log().all()
                        .statusCode(201)
                        .extract()
                        .header("Location");

        assertThat(location).contains("/users/toto");
    }
}
