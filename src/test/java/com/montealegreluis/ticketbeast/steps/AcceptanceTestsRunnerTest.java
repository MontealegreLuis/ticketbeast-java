package com.montealegreluis.ticketbeast.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    format = {"pretty", "html:target/cucumber"},
    glue = {"com.montealegreluis.ticketbeast.steps"},
    features = "features"
)
public class AcceptanceTestsRunnerTest {
}
