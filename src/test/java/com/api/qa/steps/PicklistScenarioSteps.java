package com.api.qa.steps;

import com.api.qa.steplib.PicklistSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class PicklistScenarioSteps {

    @Steps private PicklistSteps picklistSteps;

    @Given("the (user/researcher) filters the {string} search results by {string}")
    public void the_user_filters_the_search_results_by(String searchScope, String activity) {
        picklistSteps.filter_by_activity(searchScope, activity);
    }

    @Then("the users results should contain {string}")
    @And("the user results should also contain {string}")
    public void the_user_results_should_contain(String key) {
        picklistSteps.verify_that_list_of_users_are_retrieved(key, true);
    }

    @Then("the list of countries results should be filtered")
    public void the_list_of_countries_results_should_be_filtered() {
        picklistSteps.verify_that_list_of_countries_are_retrieved(true);
    }

    @Then("the list of business areas results should (have/contain) {string}")
    public void the_list_of_business_areas_results_should_contain(String key) {
        picklistSteps.verify_that_list_of_business_areas_are_retrieved(key, true);
    }
}
