package com.mastek.dna.e2e.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import com.mastek.dna.e2e.TestConfiguration;
import com.mastek.dna.e2e.UniqueTestKeyProvider;

@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext
public class BaseSteps
{
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	protected UniqueTestKeyProvider uniqueTestKeyProvider;
}
