package com.poetry.dao;

import com.poetry.model.Reply;

public class
ReplyDao
extends AbstractDao
{
	
	public String newReply( Reply reply )
	{
		final String id = generateId( reply );
		reply.setId( id );
		insert( reply );
		return id;
	}
	
	public void deleteReply( final String id )
	{
		delete( new Reply( id ) );
	}
}
