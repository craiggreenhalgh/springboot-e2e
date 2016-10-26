package com.mastek.dna.e2e.query;

public final class IndividualQuery
{
	private IndividualQuery()
	{
		// Hidden by design
	}

	public static final String INDIVIDUAL_BY_ID = "SELECT * FROM individual INNER JOIN address ON individual.id = address.individual_id WHERE individual.id = ?";
}
