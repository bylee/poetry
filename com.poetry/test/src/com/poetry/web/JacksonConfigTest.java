package com.poetry.web;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.poetry.model.Poet;
import com.poetry.model.Poetry;


public class JacksonConfigTest {
	@Test
	public
	void
	test_postProcessAfterInitialization()
	throws Exception
	{
		JacksonConfig config = new JacksonConfig();
		final MappingJackson2HttpMessageConverter converter =
			new MappingJackson2HttpMessageConverter();
		config.postProcessAfterInitialization( converter, "converter" );
		final Poetry poetry = new Poetry( "hello", new Poet( "bylee", "bonyong" ), "world", "image" );
		final HttpOutputMessage messageMock = mock( HttpOutputMessage.class );
		final HttpHeaders headersMock = mock( HttpHeaders.class );
		final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		when( messageMock.getHeaders() ).thenReturn( headersMock );
		when( messageMock.getBody() ).thenReturn( byteOut );
		
		converter.write( poetry, MediaType.APPLICATION_JSON, messageMock );
		
		assertTrue( new String( byteOut.toByteArray() ).contains( "\n" ) );
		
	}

}
