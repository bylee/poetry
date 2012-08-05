package com.poetry.service;

import org.junit.Test;

import com.poetry.AbstractTestCase;
import com.poetry.model.Binary;


public class
BinaryServiceImplTest
extends AbstractTestCase
{
	@Test
	public void test_analyze() throws Exception
	{
		final MissionService target = new MissionService();
		
		final Binary desert = new Binary();
		desert.setMime( "image/jpg" );
		desert.setContents( load( "Desert.jpg" ) );
		
		final Binary penguins = new Binary();
		penguins.setMime( "image/jpg" );
		penguins.setContents( load( "Penguins.jpg" ) );
		
		System.out.println( target.analyze( desert ) );
		System.out.println( target.analyze( penguins ) );
	}

}
