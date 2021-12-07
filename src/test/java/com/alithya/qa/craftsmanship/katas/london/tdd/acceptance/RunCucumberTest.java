package com.alithya.qa.craftsmanship.katas.london.tdd.acceptance;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "html:target/cucumber-reports.html" }, features = "src/test/resources/")
public class RunCucumberTest {

}