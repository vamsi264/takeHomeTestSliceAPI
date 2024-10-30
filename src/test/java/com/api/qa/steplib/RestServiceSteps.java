package com.api.qa.steplib;

import com.api.qa.core.RestService;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class RestServiceSteps extends ScenarioSteps {

    protected RequestSpecification request;
    protected RestService restService;
    private JsonPath json;
    protected String responseAsHTML;

    protected void setHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Authorization", format("Bearer %s", System.getenv("API_TOKEN")));

        restService = new RestService(request);
        request = restService.request(headers);
    }

    protected void setResponse(ResponseBodyExtractionOptions response) {
        json = new JsonPath(response.jsonPath().prettyPrint());
        System.out.printf("response: %s", response.asPrettyString());
    }

    protected void setResponseAsHTML(ResponseBodyExtractionOptions response) {
        responseAsHTML = response.htmlPath().prettyPrint();
    }

    protected List<Object> getResponseCollection(String key) {
        return json.getList(key);
    }

    protected Object getResponse(String key) {
        return json.get(key);
    }
}
