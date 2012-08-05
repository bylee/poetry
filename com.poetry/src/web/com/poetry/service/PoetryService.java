package com.poetry.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.poetry.dao.BookmarkDao;
import com.poetry.dao.FollowingDao;
import com.poetry.dao.MissionPoetDao;
import com.poetry.dao.PoetryDao;
import com.poetry.dao.ReplyDao;
import com.poetry.dao.StarDao;
import com.poetry.dao.TodayDao;
import com.poetry.model.Bookmark;
import com.poetry.model.Following;
import com.poetry.model.MissionPoetry;
import com.poetry.model.Poetry;
import com.poetry.model.Reply;
import com.poetry.model.Star;
import com.poetry.model.Today;
import com.poetry.util.SignUtils;

import escode.util.Assert;

@Service
public class
PoetryService
{
	protected static Random random = new Random( System.currentTimeMillis() );
	
	@Autowired( required = false )
	protected TodayDao todayDao;
	
	@Autowired( required = false )
	protected PoetryDao poetryDao;
	
	@Autowired( required = false )
	protected ReplyDao replyDao;
	
	@Autowired( required = false )
	protected StarDao starDao;
	
	@Autowired( required = false )
	protected MissionPoetDao missionPoetDao;
	
	@Autowired( required = false )
	protected BookmarkDao bookmarkDao;
	
	@Autowired( required = false )
	protected FollowingDao followingDao;
	
	public
	Poetry
	addNewPoetry(
		final Poetry poetry
	)
	{
		
		poetryDao.addNewPoetry( poetry );
		return poetry;
	}
	
	public
	Poetry
	addMissionPoetry(
		final Poetry poetry
	)
	{
		addNewPoetry( poetry );
		missionPoetDao.addMissionPoet( new MissionPoetry( poetry.getId() ) );
		
		return poetry;
	}
	
	public
	void
	addDetailInformation(
		final Poetry poetry,
		final String username
	)
	{
		final String poetryId = poetry.getId();
		poetry.setReplys( replyDao.getTheNumberOfReply( poetryId ) );
		poetry.setStars( starDao.getTheNumberOfStar( poetryId ) );
		if ( null != username )
		{
			return ;
		}
		poetry.setStar( starDao.exists( poetryId, username ) );
		poetry.setBookmark( bookmarkDao.exists( poetryId, username ) );
		poetry.setFollowing( followingDao.exists( poetry.getAuthor().getUsername(), username ) );
	
	}


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
			final Poetry poetry = poetryDao.getPoetry( poetryId );
			addDetailInformation( poetry, null );
			poetries.add( poetry );
		}
		
		poetries.addAll( poetryDao.listPoetry() );
		
		return poetries.subList( 0, Math.min( poetries.size(), 5 ) );
	}

	public
	List<Poetry>
	getPoetiesOf(
		final String poetId
	)
	{
		final List<Poetry> poetries = poetryDao.getPoetryOf( poetId );
		
		for ( final Poetry poetry : poetries )
		{
			addDetailInformation( poetry, null );
		}
		
		return poetries;

		
	}

	public
	Poetry
	getPoetry(
		final String poetryId
	)
	{
		final Poetry poetry = poetryDao.getPoetry( poetryId );
		
		final int nReply = replyDao.getTheNumberOfReply( poetryId );
		poetry.setReplys( nReply );
		final int nStar = starDao.getTheNumberOfStar( poetryId );
		poetry.setStars( nStar );


		final Authentication auth =
			SecurityContextHolder.getContext().getAuthentication();
		if ( null == auth || !auth.isAuthenticated() )
		{
			return poetry;
		}
		final String userId = auth.getName();
		addDetailInformation( poetry, userId );

		return poetry;

	}
	
	public
	void
	addStar(
		final String poetryId
	)
	{
		Assert.isTrue( SignUtils.isSignIn() );
		starDao.addStar( new Star( poetryId, SignUtils.getSignedInUsername() ) );
	}
	
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
		final List<Poetry> poetries = bookmarkDao.getBookmarksOf( poetId );
		
		for ( final Poetry poetry : poetries )
		{
			addDetailInformation( poetry, null );
		}
		
		return poetries;
	}
	

	public
	void
	addReply(
		final Reply reply
	)
	{
		reply.setCreateDate( new Date() );
		replyDao.addReply( reply );
	}

	public
	List<Reply>
	list(
		final String targetId,
		final String start
	)
	{
		return replyDao.list( targetId );
	}

	public
	List<Poetry>
	getNewsfeed(
		final String username,
		final String start
	)
	{
		if ( null == start )
		{
			return poetryDao.getNewsfeed( username );
		}
		else
		{
			return poetryDao.getNewsfeed( username, start );
		}
		
	}


}
