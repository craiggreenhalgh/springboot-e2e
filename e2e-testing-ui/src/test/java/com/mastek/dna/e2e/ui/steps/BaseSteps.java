package com.mastek.dna.e2e.ui.steps;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import com.mastek.dna.e2e.ui.TestConfiguration;
import com.paulhammant.ngwebdriver.NgWebDriver;

@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext
public class BaseSteps
{
	@Autowired
	protected WebDriver webDriver;

	@Autowired
	protected NgWebDriver ngWebDriver;

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Value("${baseUrl}")
	protected String baseUrl;
}
