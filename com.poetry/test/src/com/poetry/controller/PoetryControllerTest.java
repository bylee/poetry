package com.poetry.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poetry.servlet.MockWebApplication;
import com.poetry.servlet.MockWebApplicationContextLoader;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(
	loader = MockWebApplicationContextLoader.class,
	locations = { "classpath:com/poetry/controller/PoetryControllerTest-context.xml" }
)
@MockWebApplication( name="sample" )
public class
PoetryControllerTest
extends AbstractControllerTest
{
	@Test
	public
	void
	test_getPoetry()
	throws Exception
	{
		final Object[][] TEST_CASES = new Object[][] {
			new Object[] { "/poetry/poetry1", "GET", null, "\"title\"" },
			new Object[] { "/poetry/poetry2", "GET", null, "\"title\"" },
			new Object[] { "/poetry/poetry3", "GET", null, "\"title\"" },
			new Object[] { "/poetry/poetry4", "GET", null, "\"title\"" },
		};
		
		for ( final Object[] TEST_CASE : TEST_CASES )
		{
			final String path = (String) TEST_CASE[0];
			final String method = (String) TEST_CASE[1];
			final String headerContaining = (String) TEST_CASE[2];
			final String bodyContaining = (String) TEST_CASE[3];
			
			final MockHttpServletResponse res = request( path, method );
			assertResponse( path + "(" + method + ") failed", res, headerContaining, bodyContaining );
		}
	}

	@Test
	public
	void
	test_getReply()
	throws Exception
	{
		final Object[][] TEST_CASES = new Object[][] {
			new Object[] { "/reply/poetry1", "GET", null, "wonderful" },
		};
		
		for ( final Object[] TEST_CASE : TEST_CASES )
		{
			final String path = (String) TEST_CASE[0];
			final String method = (String) TEST_CASE[1];
			final String headerContaining = (String) TEST_CASE[2];
			final String bodyContaining = (String) TEST_CASE[3];
			
			final MockHttpServletResponse res = request( path, method );
			assertResponse( path + "(" + method + ") failed", res, headerContaining, bodyContaining );
		}
		
	}
	
}
