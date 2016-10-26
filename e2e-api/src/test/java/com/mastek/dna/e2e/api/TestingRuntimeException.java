package com.mastek.dna.e2e.api;

public class TestingRuntimeException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public TestingRuntimeException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
