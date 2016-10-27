package com.mastek.dna.e2e.ui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.paulhammant.ngwebdriver.NgWebDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;

@Configuration
@ComponentScan(basePackages = "com.mastek.dna.e2e")
@PropertySource("classpath:it.properties")
public class TestConfiguration
{
	@Value("${baseUrl}")
	private String baseUrl;

	@Value("${driver}")
	private String driver;

	@Autowired
	private WebDriver webDriver;

	@Bean
	public NgWebDriver getNgWebDriver()
	{
		return new NgWebDriver(JavascriptExecutor.class.cast(webDriver));
	}

	@Bean
	public WebDriver getWebDriver()
	{
		switch (driver)
		{
			case "chrome":
				ChromeDriverManager.getInstance().setup();
				return new ChromeDriver();
			default:
				throw new IllegalArgumentException("Unknown driver " + driver);
		}
	}
}
