package com.mastek.dna.e2e.factory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.mastek.api.Address;
import com.mastek.api.Individual;
import com.mastek.api.Name;
import com.mastek.api.Title;

public final class IndividualFactory
{
	private IndividualFactory()
	{
		// Hidden by design
	}

	public static Individual fromStoryData(final Map<String, String> data, final String key)
	{
		return new Individual()
				.withName(nameFromStoryData(data, key))
				.withDob(data.get("dob"));
	}

	public static Name nameFromStoryData(final Map<String, String> data, final String key)
	{
		return new Name()
				.withTitle(Title.valueOf(data.get("Title").toUpperCase(Locale.UK)))
				.withFirstname(data.get("firstName").concat(key))
				.withSurname(data.get("lastName"));
	}

	public static Set<Address> addressesFromStoryData(final String data)
	{
		final Set<Address> addresses = new HashSet<>();

		Arrays.stream(data.split(";")).collect(Collectors.toList()).forEach(addressString ->
		{
			final List<String> addressParams = Arrays.stream(addressString.split(",")).collect(Collectors.toList());
			addresses.add(addressFromStoryData(addressParams));
		});

		return addresses;
	}

	private static Address addressFromStoryData(final List<String> addressParts)
	{
		return new Address()
				.withLine1(addressParts.get(0))
				.withLine2(addressParts.get(1))
				.withCounty(addressParts.get(2))
				.withCountry(addressParts.get(3))
				.withPostCode(addressParts.get(4));
	}
}
