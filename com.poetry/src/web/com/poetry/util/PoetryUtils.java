package com.poetry.util;

import java.util.Arrays;
import java.util.List;

import com.poetry.model.Poet;
import com.poetry.model.Poetry;

public class PoetryUtils {
	public static Poetry[] strip(
		final Poetry... poetries
	)
	{
		for ( final Poetry poetry : poetries )
		{
			final Poet poet = poetry.getAuthor();
			if ( null == poet )
			{
				continue;
			}
			poet.setPassword( null );
			poet.setAuthority( null );
		}
		
		return poetries;
	}
	
	public static List<Poetry> strip(
		final List<Poetry> poetries
	)
	{
		return Arrays.asList( strip( poetries.toArray( new Poetry[0] ) ) );
	}
}
