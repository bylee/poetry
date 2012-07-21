package com.poetry.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.poetry.dao.PoetryDao;
import com.poetry.dao.ReplyDao;
import com.poetry.dao.StarDao;
import com.poetry.model.Poetry;
import com.poetry.model.Star;

public class
PoetryServiceImpl
implements PoetryService
{
	protected final Logger logger = LoggerFactory.getLogger( getClass() );
	
	@Autowired
	protected PoetryDao poetryDao;
	
	@Autowired
	protected ReplyDao replyDao;
	
	@Autowired
	protected StarDao starDao;
	
	protected
	void
	addTheNumberOfReply(
		final Poetry poetry
	)
	{
		logger.trace( "add the number of reply :{}", poetry );
		final int nReply = replyDao.getTheNumberOfReply( poetry.getId() );
		poetry.setReply( nReply );
	}
	
	protected
	void
	addTheNumberOfStar(
		final Poetry poetry
	)
	{
		logger.trace( "Add the number of star :{}", poetry );
		final int nStar = starDao.getTheNumberOfStar( poetry.getId() );
		poetry.setStar( nStar );
	}
	

	@Override
	public Poetry add( final Poetry poetry )
	{
		poetryDao.insert( poetry );
		return poetry;
	}

	@Override
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
	
	@Override
	public
	Poetry
	getPoetry(
		final String poetId
	)
	{
		final Poetry poetry = poetryDao.getPoetry( poetId );
		addTheNumberOfReply( poetry );
		addTheNumberOfStar( poetry );
		
		return poetry;
	}

	@Override
	public
	void
	addStar(
		final String poetryId
	)
	{
		final Authentication auth =
			SecurityContextHolder.getContext().getAuthentication();
		if ( null == auth || "anonymousUser".equals( auth.getName() ) || auth.isAuthenticated() )
		{
			return ;
		}
		starDao.addSatr( new Star( poetryId, auth.getName() ) );
	}
	
	@Override
	public
	void
	removeStar(
		final String poetryId
	)
	{
		final Authentication auth =
			SecurityContextHolder.getContext().getAuthentication();
		if ( null == auth || "anonymousUser".equals( auth.getName() ) || auth.isAuthenticated() )
		{
			return ;
		}
		starDao.removeStar( new Star( poetryId, auth.getName() ) );
	}


}
