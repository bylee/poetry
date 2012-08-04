package com.poetry.controller;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poetry.model.Binary;
import com.poetry.service.BinaryService;
import com.poetry.servlet.MockWebApplication;
import com.poetry.servlet.MockWebApplicationContextLoader;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(
	loader = MockWebApplicationContextLoader.class,
	locations = { "classpath:com/poetry/controller/HomeControllerTest-context.xml" }
)
@MockWebApplication( name="sample" )
public class
HomeControllerTest
extends AbstractControllerTest
{
	@Autowired
	protected BinaryService binaryService;
	
	@Test
	public void test_getBinary() throws Exception
	{
		final Object[][] TEST_CASES = new Object[][] {
			new Object[] { "/binary/image1", "GET", "image/jpeg", null },
		};
		
		when( binaryService.getBinary( "image1" ) ).thenReturn( new Binary( "image1", "hello.jpeg", "bylee", "image/jpeg", null ) );
		
		
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
