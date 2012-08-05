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
import com.poetry.model.Reply;
import com.poetry.service.PoetryService;
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
	@Autowired
	protected PoetryService poetryService;
	
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
		
		when( poetryService.getPoetry( "poetry1", null ) ).thenReturn( new Poetry( "Poetry1", new Poet( "bylee", "Bon-Yong Lee" ), "Hello, world", "aaaaa" ) );
		when( poetryService.getPoetry( "poetry2", null ) ).thenReturn( new Poetry( "Poetry1", new Poet( "bylee", "Bon-Yong Lee" ), "Hello, world", "aaaaa" ) );
		when( poetryService.getPoetry( "poetry3", null ) ).thenReturn( new Poetry( "Poetry1", new Poet( "bylee", "Bon-Yong Lee" ), "Hello, world", "aaaaa" ) );
		when( poetryService.getPoetry( "poetry4", null ) ).thenReturn( new Poetry( "Poetry1", new Poet( "bylee", "Bon-Yong Lee" ), "Hello, world", "aaaaa" ) );
		
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
		when( poetryService.list( "reply1", null ) )
		.thenReturn( Arrays.asList( new Reply( "reply1", "poetry1", new Poet( "bylee", "Bon-Yong Lee" ), "wonderfull" ) ) );
		
		final Object[][] TEST_CASES = new Object[][] {
			new Object[] { "/reply/reply1", "GET", null, "wonderful" },
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
