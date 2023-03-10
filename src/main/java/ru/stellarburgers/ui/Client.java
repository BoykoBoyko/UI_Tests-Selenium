package ru.stellarburgers.ui;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static ru.stellarburgers.ui.Constants.BASE_URL;

public class Client {
    public RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
}
