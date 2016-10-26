package com.mastek.dna.e2e.ui;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "com.mastek.dna.e2e.ui.steps", features = "classpath:features/", plugin = { "pretty",
		"html:target/site/cucumber-pretty",
		"json:target/cucumber.json",
		"usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" })
public class RunnerIT
{

}
