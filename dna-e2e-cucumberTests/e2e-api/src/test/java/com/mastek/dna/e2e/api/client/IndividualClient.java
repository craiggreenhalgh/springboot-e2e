package com.mastek.dna.e2e.api.client;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mastek.api.Address;
import com.mastek.api.Individual;

@Component
public class IndividualClient extends BaseClient
{
	private static final String INDIVIDUAL_URI = "/individual";
	private static final String ADDRESS_URI = "/individual/{individualId}/address";

	public Individual create(final Individual individual)
	{
		final ResponseEntity<Individual> response = restTemplate.exchange(
				baseUrl + INDIVIDUAL_URI,
				HttpMethod.POST,
				getEntity(individual), Individual.class);

		Assert.assertThat("Unexpected HttpStatus", response.getStatusCode(), Is.is(HttpStatus.CREATED));

		return response.getBody();
	}

	public Set<Address> updateAddresses(final int individualId, final Set<Address> addresses)
	{
		final Set<Address> createdAddresses = new HashSet<>();

		addresses.forEach(address ->
		{
			final ResponseEntity<Address> response = restTemplate.exchange(
					baseUrl + ADDRESS_URI,
					HttpMethod.POST,
					getEntity(address),
					Address.class,
					individualId);

			Assert.assertThat("Unexpected HttpStatus", response.getStatusCode(), Is.is(HttpStatus.CREATED));

			createdAddresses.add(response.getBody());
		});

		return createdAddresses;
	}
}
