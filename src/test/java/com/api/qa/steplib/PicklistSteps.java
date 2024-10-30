package com.api.qa.steplib;

import com.api.qa.core.Activity;
import io.restassured.response.ResponseBodyExtractionOptions;
import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class PicklistSteps extends RestServiceSteps {

    private ResponseBodyExtractionOptions response;

    @Step public void filter_by_activity(String searchScope, String activity) {
        setHeaders();
        response = restService.get(format("%s/picklist?type=%s", searchScope, Activity.valueOf(activity.toUpperCase()).name().toLowerCase()), SC_OK);
        setResponse(response);
    }

    @Step public void verify_that_list_of_users_are_retrieved(String key, boolean expected) {
        assertThat(
                getResponseCollection(format("users.%s", key)).stream().allMatch(Objects::nonNull)
        ).isEqualTo(expected);
    }

    @Step public void verify_that_list_of_countries_are_retrieved(boolean expected) {
        assertThat(
                StringUtils.isNotBlank(response.asPrettyString())
        ).isEqualTo(expected);
    }

    @Step public void verify_that_list_of_business_areas_are_retrieved(String key, boolean expected) {
        assertThat(
                getResponseCollection(format("data.%s", key)).stream().allMatch(Objects::nonNull)
        ).isEqualTo(expected);
    }
}
