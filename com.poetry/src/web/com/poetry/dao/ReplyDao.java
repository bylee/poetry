package com.poetry.dao;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poetry.model.Reply;

@Repository
public class
ReplyDao
extends AbstractDao
{
	
	public String addReply( Reply reply )
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

	public Reply get( String id )
	{
		return get( Reply.class, id );
	}

	@SuppressWarnings("unchecked")
	public
	List<Reply>
	list(
		final String id
	)
	{
		return (List<Reply>) find( "from Reply reply where reply.targetId = ? order by reply.id desc", id );
	}

	@SuppressWarnings("unchecked")
	public
	List<Reply>
	list(
		final String id,
		final String start
	)
	{
		return (List<Reply>) find(
			"from Reply reply " +
			"where reply.targetId = ? " +
			"and reply.id < ? " +
			"order by reply.id desc",
			id, start
		);
	}

	public
	int
	getTheNumberOfReply(
		final String targetId
	)
	{
		getSession().flush();
		final String query = 
			MessageFormat.format( "select count(reply.id) from Reply reply where reply.targetId = ''{0}''" , targetId );
		return ( (Long) getSession().createQuery( query ).uniqueResult() ).intValue();
	}
	
}
