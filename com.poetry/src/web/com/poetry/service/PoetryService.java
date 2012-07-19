package com.poetry.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.poetry.dao.PoetryDao;
import com.poetry.dao.ReplyDao;
import com.poetry.model.Poetry;

public class
PoetryService
{
	protected final Logger logger = LoggerFactory.getLogger( getClass() );
	@Autowired
	protected PoetryDao poetryDao;
	
	@Autowired
	protected ReplyDao replyDao;
	
	protected
	void
	addTheNumberOfReply(
		final Poetry poetry
	)
	{
		logger.trace( "add the number of reply :{}", poetry );
		final int nReply = (int) replyDao.getNumberOfReply( poetry.getId() );
		poetry.setReply( nReply );
	}

	public Poetry add( final Poetry poetry )
	{
		poetryDao.insert( poetry );
		return poetry;
	}


	public
	List<Poetry>
	getTodayPoetries(
		final String date
	)
	{
		List<Poetry> poetries = null;
		if ( null == date )
		{
			poetries = poetryDao.listPoetry();
		}
		else
		{
			poetries = poetryDao.listPoetryAfter( date );
		}
		
		for ( final Poetry poetry : poetries )
		{
			addTheNumberOfReply( poetry );
		}
		
		return poetries;
	}
	
	public Poetry getPoetry( final String id )
	{
		final Poetry poetry = poetryDao.getPoetry( id );
		addTheNumberOfReply( poetry );
		
		return poetry;
	}


}
