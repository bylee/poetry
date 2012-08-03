package com.poetry.controller;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.PassThroughFilterChain;
import org.springframework.web.servlet.DispatcherServlet;

public class AbstractControllerTest
{
	protected Logger logger = LoggerFactory.getLogger( getClass() );

	protected Filter filter;
	
	@Autowired
	DispatcherServlet servlet;

	protected
	void
	assertResponse(
		final String message,
		final MockHttpServletResponse res,
		final String headerContaining,
		final String bodyContaining
	)
	throws UnsupportedEncodingException
	{
		if ( null != headerContaining )
		{
			final Set<String> headerNames = res.getHeaderNames();
			boolean bContaining = false;
			for ( final String name : headerNames )
			{
				String contents = res.getHeader( name );
				logger.info( "Name :{}, Contents :{}", name, contents );
				if ( contents.contains( headerContaining ) )
				{
					bContaining = true;
				}
			}
			assertTrue( message + "\nHeader Name :" + headerNames, bContaining );

		}
		
		if ( null != bodyContaining )
		{
			final String contents = res.getContentAsString();
			logger.info( "Contents :{}", contents );
			assertTrue(
				message + "\nBody :" + contents,
				contents.contains( bodyContaining )
			);

		}
		
	}

	protected
	MockHttpServletResponse
	request(
		final String path,
		final String method
	)
	throws ServletException, IOException
	{
		logger.trace( "Path :{}, method :{}", path, method );
		final MockHttpServletRequest req = new MockHttpServletRequest( method, path );
		final MockHttpServletResponse res = new MockHttpServletResponse();
		
		final PassThroughFilterChain chain = new PassThroughFilterChain( servlet );
		
		chain.doFilter( req, res );
		return res;
		
	}


}
