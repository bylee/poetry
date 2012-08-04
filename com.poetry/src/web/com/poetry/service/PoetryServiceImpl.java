package com.poetry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.poetry.dao.BookmarkDao;
import com.poetry.dao.FollowingDao;
import com.poetry.dao.PoetDao;
import com.poetry.dao.PoetryDao;
import com.poetry.dao.ReplyDao;
import com.poetry.dao.StarDao;
import com.poetry.dao.TodayDao;
import com.poetry.model.Bookmark;
import com.poetry.model.Following;
import com.poetry.model.Poetry;
import com.poetry.model.PoetryStatus;
import com.poetry.model.Star;
import com.poetry.model.Today;
import com.poetry.util.SignUtils;

import escode.util.Assert;

public class
PoetryServiceImpl
implements PoetryService
{
	protected final Logger logger = LoggerFactory.getLogger( getClass() );
	
	protected static Random random = new Random( System.currentTimeMillis() );
	
	@Autowired
	protected TodayDao todayDao;
	
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
	getTodayPoetries()
	{
		final List<Poetry> poetries = new ArrayList<Poetry>();
		final List<String> todayCandidates = poetryDao.getTodayPoetryCandidates();
		if ( 0 < todayCandidates.size() )
		{
			final int nPick = random.nextInt( todayCandidates.size() );
			final String poetryId = todayCandidates.get( nPick );
			poetries.add( poetryDao.getPoetry( poetryId ) );
		}
		
		poetries.addAll( poetryDao.listPoetry() );
		
		return poetries.subList( 0, Math.min( poetries.size(), 5 ) );
	}

	@Override
	public
	List<Poetry>
	getPoetiesOf(
		final String poetId
	)
	{
		return poetryDao.getPoetryOf( poetId );
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
		Assert.isTrue( SignUtils.isSignIn() );
		starDao.addStar( new Star( poetryId, SignUtils.getSignedInUsername() ) );
	}
	
	@Override
	public
	void
	removeStar(
		final String poetryId
	)
	{
		Assert.isTrue( SignUtils.isSignIn() );
		starDao.removeStar( new Star( poetryId, SignUtils.getSignedInUsername() ) );
	}

	public
	void
	addBookmark(
		final String poetryId
	)
	{
		Assert.isTrue( SignUtils.isSignIn() );
		bookmarkDao.addBookmark( new Bookmark( poetryId, SignUtils.getSignedInUsername() ) );
	}

	public
	void
	removeBookmark( String poetryId )
	{
		Assert.isTrue( SignUtils.isSignIn() );
		bookmarkDao.removeBookmark( new Bookmark( poetryId, SignUtils.getSignedInUsername() ) );
	}

	public 
	void
	addFollowing( String poetId )
	{
		Assert.isTrue( SignUtils.isSignIn() );
		followingDao.addFollowing( new Following( poetId, SignUtils.getSignedInUsername() ) );
	}

	public 
	void
	removeFollowing( String poetId )
	{
		Assert.isTrue( SignUtils.isSignIn() );
		followingDao.addFollowing( new Following( poetId, SignUtils.getSignedInUsername() ) );
	}

	public 
	void
	setTodayPoetry( Today today )
	{
		todayDao.setToday( today );
	}

	public
	List<Poetry>
	getTodayCandidates(
		final String start
	)
	{
		return null;
	}

	public List<Poetry> getBookmarksOf(
		final String poetId
	)
	{
		return bookmarkDao.getBookmarksOf( poetId );
	}
	


}
