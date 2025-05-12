package com.fiap.challenge.fast_food_catalog.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.fiap.challenge.fast_food_catalog.bdd.steps"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "junit:target/cucumber.xml"}
)
public class RunCucumberTest {
}
