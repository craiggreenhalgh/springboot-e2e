package com.mastek.dna.e2e.ui.steps;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class IndividualSteps extends BaseSteps
{
	@Given("^I open up the home page")
	public void i_open_up_the_login_page()
	{
		webDriver.navigate().to(baseUrl);
	}

	@When("^I click the individual link")
	public void i_click_the_individual_link()
	{
		webDriver.findElement(By.linkText("Individual")).click();
	}

	@Then("^The individual list page is shown")
	public void the_individual_list_page_is_shown()
	{
		Assert.assertThat("Unexpected URL", webDriver.getCurrentUrl(), Is.is(baseUrl + "/individual"));

		Assert.assertThat("Page title not found",
				webDriver.findElement(By.className("page-header")).getText(),
				Is.is("DNA User List"));
	}
}
