package com.poetry.util;

import java.util.Arrays;
import java.util.List;

import com.poetry.model.Poet;
import com.poetry.model.Reply;

public class ReplyUtils {
	public static Reply[] strip(
		final Reply... poetries
	)
	{
		for ( final Reply poetry : poetries )
		{
			final Poet poet = poetry.getWriter();
			if ( null == poet )
			{
				continue;
			}
			poet.setPassword( null );
			poet.setAuthority( null );
		}
		
		return poetries;
	}
	
	public static List<Reply> strip(
		final List<Reply> poetries
	)
	{
		return Arrays.asList( strip( poetries.toArray( new Reply[0] ) ) );
	}
}
