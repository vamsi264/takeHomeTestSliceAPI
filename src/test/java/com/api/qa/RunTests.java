package com.api.qa;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features"
)
public class RunTests {

    @BeforeClass
    public static void setUp() {
        System.out.println("--- Before Suite ---");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("--- After Suite ---");
    }
}