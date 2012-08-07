package com.poetry.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.poetry.model.Poet;
import com.poetry.model.SignStatus;
import com.poetry.service.PoetService;
import com.poetry.util.SignUtils;

@Controller
public class
SignController
extends AbstractController
{

	@Autowired
	@Qualifier( "authenticationManager" )
	protected AuthenticationManager authenticationManager;
	
	@Autowired
	protected PoetService poetService;

	@RequestMapping(
		value = "/service/signstatus",
		method = RequestMethod.GET
	)
	@ResponseBody
	public
	SignStatus
	getStatus()
	{
		if ( SignUtils.isSignIn() )
		{
			return new SignStatus( SignUtils.getSignedInUsername(), "success" );
		} else {
			return new SignStatus( null, "fail" );
		}
	}

	@RequestMapping(
		value = "/service/signin",
		method = RequestMethod.POST
	)
	@ResponseBody
	public
	SignStatus
	login(
		@RequestParam("j_username") String username,
		@RequestParam("j_password") String password,
		HttpServletResponse response
	)
	{

		final UsernamePasswordAuthenticationToken token =
			new UsernamePasswordAuthenticationToken( username, password );
		final Poet details = new Poet();
		details.setUsername( username );
		token.setDetails( details );

		try {
			final Authentication auth = authenticationManager.authenticate( token );
			SecurityContextHolder.getContext().setAuthentication( auth );
			final Cookie cookie = new Cookie( "username", username );
			cookie.setMaxAge( 0 );
			response.addCookie( cookie );
			return new SignStatus( auth.getName(), "success" );
		} catch (BadCredentialsException e) {
			return new SignStatus( null, "fail" );
		}
	}

	@RequestMapping(
		value = "/service/signout"
	)
	public
	void
	logout(
		final HttpServletResponse response
	)
	{
		final Cookie cookie = new Cookie( "username", null );
		cookie.setMaxAge( 0 );

		response.addCookie( cookie );
	}
	
	@RequestMapping(
		value = "/service/signup"
	)
	@ResponseBody
	public
	SignStatus
	signup(
		final Poet poet,
		final HttpServletResponse response
	)
	{
		poetService.addNewPoet( poet );
		return login( poet.getUsername(), poet.getPassword(), response );
	}
	
	@RequestMapping(
			value = "/service/signin", 
			method = RequestMethod.GET
	)
	public 
	void  
	loginGet(
			@RequestParam("j_username") String username,
			@RequestParam("j_password") String password,
			@RequestParam("callbackFn") String callbackFn,
	HttpServletResponse response,
	HttpServletRequest request) throws IOException {

		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);
		final Poet details = new Poet();
		details.setUsername(username);
		token.setDetails(details);

		try {
			final Authentication auth = authenticationManager
					.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			final Cookie jcookie = new Cookie("JSESSIONID", request.getSession().getId());
			final Cookie ucookie = new Cookie("username", username);
			jcookie.setMaxAge(0);
			ucookie.setMaxAge(0);
			//response.addCookie(jcookie);
			response.addCookie(ucookie);
			SignStatus sign= new SignStatus( auth.getName(), "success" );
			response.setContentType("text/javascript; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(callbackFn+"(" +
					"{" + 
						"status : 'success'," +
						"jsessionid : '"+request.getSession().getId()+"'," +
						"username : '"+username + "'" +
					"}" +
					")");
			out.close();
			response.flushBuffer();
		} catch (BadCredentialsException e) {
			response.setContentType("text/javascript; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(callbackFn+"({status:'fail'})");
			out.close();
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
