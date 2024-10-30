package com.api.qa.core;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

public class Properties {

    public static Scenario scenario;
    private static final Logger LOGGER = LoggerFactory.getLogger(Properties.class);

    @Before
    public void setup(Scenario scenario) {
        Properties.scenario = scenario;
    }

    public static void log(String text) {
        LOGGER.info(text);
        scenario.log(text);
    }

    public String getEnvironment() {
        return getProperty("environment");
    }

    public String getVersion() {
        return getProperty("version");
    }

    private String getProperty(String key) {

        if (StringUtils.isEmpty(key)) {
            String message = format("%s cannot be null, empty or blank.", key);
            LOGGER.error(message);
            throw new IllegalArgumentException(message);
        }

        EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();
        return EnvironmentSpecificConfiguration.from(env).getProperty(key);
    }
}
