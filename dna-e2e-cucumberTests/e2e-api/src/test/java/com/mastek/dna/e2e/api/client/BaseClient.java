package com.mastek.dna.e2e.api.client;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastek.dna.e2e.api.TestingRuntimeException;

public class BaseClient
{
	@Value("${baseUrl}")
	protected String baseUrl;

	@Value("${api.username}")
	private String apiUsername;

	@Value("${api.password}")
	private String apiPassword;

	@Autowired
	protected RestTemplate restTemplate;

	protected final <I> HttpEntity<String> getEntity(final I entity)
	{
		return new HttpEntity<String>(getRequestJson(entity), getHeaders());
	}

	private final <I> String getRequestJson(final I toSend)
	{
		final ObjectMapper objMapper = getObjectMapper();
		try
		{
			return objMapper.writeValueAsString(toSend);
		}
		catch (final JsonProcessingException jpe)
		{
			throw new TestingRuntimeException("Error creating JSON body", jpe);
		}
	}

	protected ObjectMapper getObjectMapper()
	{
		return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	}

	protected MultiValueMap<String, String> getHeaders()
	{
		final HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		addSecurityHeader(headers);
		return headers;
	}

	private void addSecurityHeader(final HttpHeaders headers)
	{
		final byte[] encodedAuth = Base64.getEncoder().encode(
				String.format("%s:%s", apiUsername, apiPassword)
						.getBytes(Charset.forName("UTF-8")));
		headers.set("Authorization", "Basic " + new String(encodedAuth));
	}
}
