package com.poetry.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.poetry.dao.BookmarkDao;
import com.poetry.dao.FollowingDao;
import com.poetry.dao.PoetryDao;
import com.poetry.dao.ReplyDao;
import com.poetry.dao.StarDao;
import com.poetry.model.Bookmark;
import com.poetry.model.Following;
import com.poetry.model.Poetry;
import com.poetry.model.PoetryStatus;
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
	
	@Autowired
	protected BookmarkDao bookmarkDao;
	
	@Autowired
	protected FollowingDao followingDao;
	
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
		
		return poetries;
	}
	
	@Override
	public
	Poetry
	getPoetry(
		final String poetryId
	)
	{
		final Poetry poetry = poetryDao.getPoetry( poetryId );
		return poetry;
	}
	
	@Override
	public
	PoetryStatus
	getPoetryStatus(
		final String poetryId
	)
	{
		final PoetryStatus status = new PoetryStatus();
		
		final Authentication auth =
			SecurityContextHolder.getContext().getAuthentication();
		if ( null == auth || !auth.isAuthenticated() )
		{
			return status;
		}
		final String userId = auth.getName();

		final int nReply = replyDao.getTheNumberOfReply( poetryId );
		status.setReply( nReply );
		final int nStar = starDao.getTheNumberOfStar( poetryId );
		status.setStar( nStar );


		status.setStar( starDao.exists( poetryId, userId ) );
		status.setBookmark( bookmarkDao.exists( poetryId, userId ) );
		Poetry poetry = poetryDao.getPoetry( poetryId );
		status.setFollowing( followingDao.exists( poetry.getAuthor().getUsername(), userId ) );
		
		return status;
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
		if ( null == auth || !auth.isAuthenticated() )
		{
			return ;
		}
		starDao.addStar( new Star( poetryId, auth.getName() ) );
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
		if ( null == auth || !auth.isAuthenticated() )
		{
			return ;
		}
		starDao.removeStar( new Star( poetryId, auth.getName() ) );
	}

	public void addBookmark( String poetryId )
	{
		final Authentication auth =
			SecurityContextHolder.getContext().getAuthentication();
		if ( null == auth || !auth.isAuthenticated() )
		{
			return ;
		}
		bookmarkDao.addBookmark( new Bookmark( poetryId, auth.getName() ) );
	}

	public void removeBookmark( String poetryId )
	{
		final Authentication auth =
			SecurityContextHolder.getContext().getAuthentication();
		if ( null == auth || !auth.isAuthenticated() )
		{
			return ;
		}
		bookmarkDao.removeBookmark( new Bookmark( poetryId, auth.getName() ) );
	}

	public void addFollowing( String poetId )
	{
		final Authentication auth =
			SecurityContextHolder.getContext().getAuthentication();
		if ( null == auth || !auth.isAuthenticated() )
		{
			return ;
		}
		followingDao.addFollowing( new Following( poetId, auth.getName() ) );
		
	}

	public void removeFollowing( String poetId )
	{
		final Authentication auth =
			SecurityContextHolder.getContext().getAuthentication();
		if ( null == auth || !auth.isAuthenticated() )
		{
			return ;
		}
		followingDao.addFollowing( new Following( poetId, auth.getName() ) );
		
	}
	


}
