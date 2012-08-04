package com.poetry.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poetry.model.Poet;
import com.poetry.model.Poetry;
import com.poetry.service.PoetryService;
import com.poetry.servlet.MockWebApplication;
import com.poetry.servlet.MockWebApplicationContextLoader;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(
	loader = MockWebApplicationContextLoader.class,
	locations = { "classpath:com/poetry/controller/TodayControllerTest-context.xml" }
)
@MockWebApplication( name="sample" )
public class
TodayControllerTest
extends AbstractControllerTest
{
	@Autowired
	protected PoetryService poetryService;
	
	@Test
	public
	void
	test_getTodayPoetry()
	throws Exception
	{
		when( poetryService.getTodayPoetries() )
		.thenReturn( Arrays.asList(
			new Poetry( "Poetry1", new Poet( "bylee", "Bon-Yong Lee" ), "Hello, world", "aaaaa" )
		) );
		final Object[][] TEST_CASES = new Object[][] {
			new Object[] { "/today/20120718", "GET", null, "\"title\"" },
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
