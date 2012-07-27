package com.poetry.util;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import com.poetry.model.Poet;
import com.poetry.model.Poetry;

public class PoetryUtils {
	public static Poetry[] strip(
		final Session session,
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
			session.evict( poet );
			poet.setPassword( null );
			poet.setAuthority( null );
		}
		
		return poetries;
	}
	
	public static List<Poetry> strip(
		final Session session,
		final List<Poetry> poetries
	)
	{
		return Arrays.asList( strip( session, poetries.toArray( new Poetry[0] ) ) );
	}
}
