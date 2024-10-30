package com.api.qa.core;

import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static com.api.qa.core.Properties.log;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;
import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.reset;

public class RestService {

    private RequestSpecification requestSpecification;
    private String endpoint;
    private final Properties properties = new Properties();

    public RestService(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public RequestSpecification request(Map<String, ?> headers) {
        reset();
        endpoint = format("%s/%s", properties.getEnvironment(), properties.getVersion());
        requestSpecification = given().relaxedHTTPSValidation()
                .contentType(JSON).headers(headers)
                .baseUri(endpoint).when();
        return requestSpecification;
    }

    public ResponseBodyExtractionOptions get(String path, int statusCode) {
        return requestSpecification.contentType(JSON).get(getEndpoint(path)).then().assertThat().statusCode(statusCode).extract();
    }

    public ResponseBodyExtractionOptions post(String path, Map<String, ?> params, int statusCode) {
        return requestSpecification.accept(JSON).body(params).contentType(JSON).post(getEndpoint(path)).then().assertThat().statusCode(statusCode).extract();
    }

    public ResponseBodyExtractionOptions put(String path, Map<String, ?> params, int statusCode) {
        return requestSpecification.with().put(getEndpoint(path), params).then().assertThat().statusCode(statusCode).extract();
    }

    public ResponseBodyExtractionOptions put(String contentType, String path, String body, int statusCode) {
        return requestSpecification.contentType(contentType).body(body).when().put(getEndpoint(path)).then().assertThat().statusCode(statusCode).extract();
    }

    public ResponseBodyExtractionOptions delete(String path, int statusCode) {
        return requestSpecification.with().delete(getEndpoint(path)).then().assertThat().statusCode(statusCode).extract();
    }

    private String getEndpoint (String path){
        endpoint = format("%s/%s", endpoint, path);
        log(format("Endpoint URL: %s", endpoint));
        return endpoint;
    }
}
