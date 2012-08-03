package com.poetry.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import com.poetry.model.SignStatus;

public class
SignControllerTest
{

	protected SignController loginService;
	
	protected AuthenticationManager authenticationManager;
	
	protected HttpServletResponse response;

	@Before
	public
	void
	setUp()
	{
		loginService = new SignController();
		authenticationManager = mock( AuthenticationManager.class );
		response = mock( HttpServletResponse.class );
		
		loginService.authenticationManager = authenticationManager;
	}

	@After
	public
	void
	tearDown()
	{
		SecurityContextHolder.clearContext();
	}

	@Test
	public
	void
	testSignStatusSuccess()
	{
		final Authentication auth = new TestingAuthenticationToken( "bylee", "bylee" );
		auth.setAuthenticated( true );
		final SecurityContext context = new SecurityContextImpl();
		context.setAuthentication( auth );
		SecurityContextHolder.setContext( context );

		final SignStatus status = loginService.getStatus();
		assertEquals( "success", status.getStatus() );
	}

	@Test
	public
	void
	testSignStatusFailure()
	{
		SignStatus status = loginService.getStatus();
		assertEquals( "fail", status.getStatus() );
	}

	@Test
	public
	void
	testGoodLogin()
	{
		final Authentication auth = new TestingAuthenticationToken( "foo", "bar" );
		auth.setAuthenticated( true );
		when( authenticationManager.authenticate( Matchers.<Authentication>anyObject() ) ).thenReturn( auth );
		final SignStatus status = loginService.login( "foo", "bar", response );
		verify( response ).addCookie( Matchers.<Cookie>any() );
		assertEquals( "success", status.getStatus() );
		assertEquals( "foo", status.getUsername() );
	}

	@Test
	public
	void
	testBadLogin()
	{
		final Authentication auth = new TestingAuthenticationToken("foo", "bar");
		auth.setAuthenticated( false );
		when( authenticationManager.authenticate( Matchers.<Authentication>anyObject() ) )
			.thenThrow( new BadCredentialsException( "Bad Credentials" ) );
		verify( response, never() ).addCookie( Matchers.<Cookie>any() );
		final SignStatus status = loginService.login( "foo", "bar", response );
		assertEquals( "fail", status.getStatus() );
		assertEquals( null, status.getUsername() );
	}
}
