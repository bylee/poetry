package com.poetry.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poetry.dao.BookmarkDao;
import com.poetry.dao.FollowingDao;
import com.poetry.dao.MissionPoetryDao;
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
extends AbstractService
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
	protected MissionPoetryDao missionPoetDao;
	
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
		missionPoetDao.addMissionPoetry( new MissionPoetry( poetry.getId() ) );
		
		return poetry;
	}
	
	public
	Poetry
	addDetailInformation(
		final Poetry poetry,
		final String username
	)
	{
		logger.trace( "add {}'s details to {}", username, poetry );
		
		final String poetryId = poetry.getId();
		poetry.setReplys( replyDao.getTheNumberOfReply( poetryId ) );
		poetry.setStars( starDao.getTheNumberOfStar( poetryId ) );
		
		logger.debug( "username :{}", username );
		if ( null == username )
		{
			return poetry;
		}
		final boolean bStar = starDao.exists( poetryId, username );
		final boolean bBookmark = bookmarkDao.exists( poetryId, username );
		final boolean bFollowing = followingDao.exists( poetry.getAuthor().getUsername(), username );
		logger.debug(
			"star :{}, bookmark :{}, following :{}",
			new Object[] { bStar, bBookmark, bFollowing }
		);
		poetry.setStar( bStar );
		poetry.setBookmark( bBookmark );
		poetry.setFollowing( bFollowing );

		
		logger.info( "poetry :{}", poetry );
		return poetry;
	}
	
	public
	List<Poetry>
	addDetailInformation(
		final List<Poetry> poetries,
		final String username
	)
	{
		for ( final Poetry poetry : poetries )
		{
			addDetailInformation( poetry, username );
		}
		
		return poetries;
		
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
			addDetailInformation( poetry, SignUtils.getSignedInUsername() );
			poetries.add( poetry );
		}
		
		poetries.addAll( addDetailInformation( poetryDao.listPoetry(), SignUtils.getSignedInUsername() ) );
		
		return poetries.subList( 0, Math.min( poetries.size(), 5 ) );
	}

	public
	List<Poetry>
	getPoetiesOf(
		final String poetId
	)
	{
		return addDetailInformation( poetryDao.getPoetryOf( poetId ), null );
	}

	public
	Poetry
	getPoetry(
		final String poetryId,
		final String username
	)
	{
		final Poetry poetry = poetryDao.getPoetry( poetryId );
		
		final int nReply = replyDao.getTheNumberOfReply( poetryId );
		poetry.setReplys( nReply );
		final int nStar = starDao.getTheNumberOfStar( poetryId );
		poetry.setStars( nStar );

		return addDetailInformation( poetry, username );

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

	public List<Poetry>
	getBookmarksOf(
		final String poetId
	)
	{
		return addDetailInformation( bookmarkDao.getBookmarksOf( poetId ), null );
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
			return addDetailInformation( poetryDao.getNewsfeed( username ), username );
		}
		else
		{
			return addDetailInformation( poetryDao.getNewsfeed( username, start ), username );
		}
		
	}


}
