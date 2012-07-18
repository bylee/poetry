package com.poetry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetry.model.User;
import com.poetry.model.SignStatus;

@Controller
public class
SignController
{

	@Autowired
	@Qualifier( "authenticationManager" )
	protected AuthenticationManager authenticationManager;

	@RequestMapping( value = "/service/signstatus", method = RequestMethod.GET )
	@ResponseBody
	public
	SignStatus
	getStatus()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ( null != auth && !auth.getName().equals("anonymousUser") && auth.isAuthenticated() )
		{
			return new SignStatus( auth.getName(), "success" );
		} else {
			return new SignStatus( null, "fail" );
		}
	}

	@RequestMapping( value = "/service/signin", method = RequestMethod.POST )
	@ResponseBody
	public
	SignStatus
	login(
		@RequestParam("j_username") String username,
		@RequestParam("j_password") String password
	)
	{

		final UsernamePasswordAuthenticationToken token =
			new UsernamePasswordAuthenticationToken( username, password );
		final User details = new User();
		details.setUsername( username );
		token.setDetails( details );

		try {
			final Authentication auth = authenticationManager.authenticate( token );
			SecurityContextHolder.getContext().setAuthentication( auth );
			return new SignStatus( auth.getName(), "success" );
		} catch (BadCredentialsException e) {
			return new SignStatus( null, "fail" );
		}
	}

}
