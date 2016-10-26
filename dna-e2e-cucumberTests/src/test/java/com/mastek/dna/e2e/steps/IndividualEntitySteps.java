package com.mastek.dna.e2e.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsNull;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Joiner;
import com.mastek.api.Address;
import com.mastek.api.Individual;
import com.mastek.dna.e2e.client.IndividualClient;
import com.mastek.dna.e2e.factory.IndividualFactory;
import com.mastek.dna.e2e.query.IndividualQuery;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class IndividualEntitySteps extends BaseSteps
{
	@Autowired
	private IndividualClient individualClient;

	private Individual created;

	private List<Map<String, Object>> dbData;

	@Given("^I create the person entity and update the person record with the following details$")
	public void i_create_the_person_entity_and_update_the_person_record_with_the_following_details(final Map<String, String> data)
	{
		final Individual individual = IndividualFactory.fromStoryData(data, uniqueTestKeyProvider.getKey());

		created = individualClient.create(individual);
		assertThat("Individual ID should not be null", created.getId(), IsNull.notNullValue());
		// TODO: Assert the rest using a Matcher

		final Set<Address> addresses = IndividualFactory.addressesFromStoryData(data.get("address"));
		final Set<Address> createAddresses = individualClient.updateAddresses(created.getId(), addresses);
		createAddresses.forEach(address -> assertThat("Address ID should not be null", address.getId(), IsNull.notNullValue()));
		// TODO: Assert the address responses using a Matcher
	}

	@When("^I get the person details from postgres db$")
	public void i_get_the_person_details_from_postgres_db()
	{
		dbData = jdbcTemplate.queryForList(IndividualQuery.INDIVIDUAL_BY_ID, created.getId());
	}

	@Then("^person details name, dob and address are as below$")
	public void person_details_name_dob_and_address_are_as_below(final Map<String, String> expectedData)
	{
		dbData.forEach(record ->
		{
			assertThat(record.get("first_name"), is(equalTo(expectedData.get("firstName").concat(uniqueTestKeyProvider.getKey()))));
			assertThat(record.get("last_name"), is(equalTo(expectedData.get("lastName"))));

			final String address = Joiner.on(",").join(
					record.get("address_line1"),
					record.get("address_line2"),
					record.get("county"),
					record.get("country"),
					record.get("post_code"));

			// TODO : This should be the other way round as the actual value
			// should always be the first parameter
			assertThat("Unexpected address", expectedData.get("address"), CoreMatchers.containsString(address));
		});
	}

}
