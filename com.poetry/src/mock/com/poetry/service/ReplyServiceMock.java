package com.poetry.service;

import java.util.ArrayList;
import java.util.List;

import com.poetry.model.Reply;

public class
ReplyServiceMock
implements ReplyService
{
	@Override
	public
	List<Reply>
	list(
		final String targetId,
		final String start
	)
	{
		final ArrayList<Reply> ret = new ArrayList<Reply>();
		
		if ( "poetry1".equals( targetId ) )
		{
			Reply r1 = new Reply();
			r1.setId( "reply1" );
			r1.setTargetId( targetId );
			r1.setContents( "wonderful" );
			ret.add( r1 );
		}
		
		return ret;
	}

	@Override
	public void addReply( Reply reply )
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Reply get( String id )
	{
		throw new UnsupportedOperationException();
	}

}
