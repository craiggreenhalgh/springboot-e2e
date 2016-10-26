package com.mastek.dna.e2e.api;

import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.mastek.dna.e2e")
@PropertySource("classpath:it.properties")
public class TestConfiguration
{
	private final static Logger LOGGER = LoggerFactory.getLogger(TestConfiguration.class);

	@Bean
	public RestTemplate createRestTemplate()
	{
		return new RestTemplate();
	}

	@Bean
	public UniqueTestKeyProvider getTestDate()
	{
		return new UniqueTestKeyProvider()
		{
			private final ZonedDateTime now = ZonedDateTime.now();

			{
				LOGGER.info("ZonedDateTime [{}]", now);
				LOGGER.info("Unique Test Key [{}]", getKey());
			}

			@Override
			public String getKey()
			{
				return String.valueOf(now.toInstant().toEpochMilli());
			}
		};
	}
}
