package com.poetry;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.PassThroughFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.DispatcherServlet;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(
	loader=MockWebApplicationContextLoader.class,
	locations= { "classpath:com/poetry/ControllerTest-context.xml" }
)
@MockWebApplication( name="sample" )
public class
HomeControllerTest
{
	protected Logger logger = LoggerFactory.getLogger( getClass() );
	
	protected Filter filter;
	
	@Autowired
	DispatcherServlet servlet;
	
	@Before
	public void setUpFilter() throws Exception
	{
		final FilterConfig config = new MockFilterConfig( servlet.getServletContext() );
		this.filter = new SessionInViewFilter();
		this.filter.init( config );
	}
	
	@After
	public void tearDownFilter()
	{
		this.filter.destroy();
	}

	@Test
	public void test_create() throws Exception
	{
		final Object[][] TEST_CASES = new Object[][] {
			new Object[] { "/today", "GET", null, "\"title\":" },
			new Object[] { "/poetry/poetry1", "GET", null, "\"title\":" },
			new Object[] { "/poetry/poetry2", "GET", null, "\"title\":" },
			new Object[] { "/poetry/poetry3", "GET", null, "\"title\":" },
			new Object[] { "/poetry/poetry4", "GET", null, "\"title\":" },
			new Object[] { "/binary/image1", "GET", "image/jpeg", null },
			new Object[] { "/reply/poetry1", "GET", null, "wonderful" },
		};
		
		for ( final Object[] TEST_CASE : TEST_CASES )
		{
			final String path = (String) TEST_CASE[0];
			final String method = (String) TEST_CASE[1];
			final String headerContaining = (String) TEST_CASE[2];
			final String bodyContaining = (String) TEST_CASE[3];
			logger.trace( "Path :{}, method :{}", path, method );
			final MockHttpServletRequest req = new MockHttpServletRequest( method, path );
			final MockHttpServletResponse res = new MockHttpServletResponse();
			
			final PassThroughFilterChain chain =
				new PassThroughFilterChain(
					filter,
					new PassThroughFilterChain( servlet )
				);
			
			chain.doFilter( req, res );
			
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
				
				assertTrue( path + "(" + method + ") failed", bContaining );

			}
			
			if ( null != bodyContaining )
			{
				final String contents = res.getContentAsString();
				logger.info( "Contents :{}", contents );
				assertTrue(
					path + "(" + method + ") failed",
					contents.contains( bodyContaining )
				);

			}
			
		}
		
	}

}
