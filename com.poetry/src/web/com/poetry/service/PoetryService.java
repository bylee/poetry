package com.poetry.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poetry.dao.BlockDao;
import com.poetry.dao.BookmarkDao;
import com.poetry.dao.FollowingDao;
import com.poetry.dao.MissionDao;
import com.poetry.dao.MissionPoetryDao;
import com.poetry.dao.PoetryDao;
import com.poetry.dao.ReplyDao;
import com.poetry.dao.StarDao;
import com.poetry.dao.TodayDao;
import com.poetry.model.Block;
import com.poetry.model.Bookmark;
import com.poetry.model.Mission;
import com.poetry.model.MissionPoetry;
import com.poetry.model.Poetry;
import com.poetry.model.Reply;
import com.poetry.model.Star;
import com.poetry.model.Today;
import com.poetry.util.DateUtils;
import com.poetry.util.SignUtils;

import escode.util.Assert;
import escode.util.ObjectUtils;

@Service
public class
PoetryService
extends AbstractService
{
	protected static Random random = new Random( System.currentTimeMillis() );
	
	@Autowired( required = false )
	protected BlockDao blockDao;
	
	@Autowired( required = false )
	protected TodayDao todayDao;
	
	@Autowired( required = false )
	protected PoetryDao poetryDao;
	
	@Autowired( required = false )
	protected ReplyDao replyDao;
	
	@Autowired( required = false )
	protected StarDao starDao;
	
	@Autowired( required = false )
	protected MissionDao missionDao;

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
		poetry.setCreatedDate( new Date() );
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
		final Mission mission = missionDao.getMission( DateUtils.getTomorrow() );
		missionPoetDao.addMissionPoetry( new MissionPoetry( poetry.getId(), mission.getId() ) );
		
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
	getTodayPoetries(
		final Date date
	)
	{
		int nPoetry = 5;
		
		final TreeMap<Today, Poetry> poetries = new TreeMap<Today, Poetry>();
		final Map<Today, Poetry> userPoetries = poetryDao.getUserSpecificTodayPoetry( date );
		logger.trace( "User specification :{}", userPoetries );

		final Iterator<Today> userIter = userPoetries.keySet().iterator();
		Today index = null;
		LinkedList<String> starCandidates = null;
		List<Poetry> remains = null;
		Iterator<Poetry> remainsIter = null;

		for ( int i = 0 ; i < nPoetry ; ++i )
		{
			if ( null == index )
			{
				if ( userIter.hasNext() ) {
					index = userIter.next();
				}
			}
			logger.trace( "Iteration :{}, index :{}", i, index );
			
			if ( null != index )
			{
				if ( nPoetry <= index.getTarget() )
				{
					index = null;
					continue;
				}
				while ( i < index.getTarget() )
				{
					// 뭘로 채울까?
					if ( null == remains )
					{
						remains = poetryDao.listPoetry();
						remainsIter = remains.iterator();
					}
					
					if ( remainsIter.hasNext() )
					{
						final Today t = new Today( new Date(), i );	// TODO
						poetries.put( t, remainsIter.next() );
					}
				}
				
				if ( "user".equals( index.getType() ) )
				{
					poetries.put( index, userPoetries.get( index ) );
					index = null;
				}
				else if ( "star".equals( index.getType() ) )
				{
					if ( null == starCandidates )
					{
						starCandidates = new LinkedList<String>( poetryDao.getMostStaredTodayPoetries( date ) );
					}
					while ( !starCandidates.isEmpty() )
					{
						final String poetryId = starCandidates.remove();
						if ( poetries.keySet().contains( poetryId ) )
						{
							continue;
						}
						poetries.put( index, addDetailInformation(
							poetryDao.getPoetry( poetryId ),
							SignUtils.getSignedInUsername()
						) );
						index = null;
						break;
					}
				}
				else
				{
					throw new IllegalArgumentException();
				}
			}
			else
			{
				if ( null == remains )
				{
					remains = poetryDao.listPoetry();
					remainsIter = remains.iterator();
				}
				
				if ( remainsIter.hasNext() )
				{
					final Today t = new Today( new Date(), i );	// TODO
					poetries.put( t, remainsIter.next() );
				}
			}
		}
		
		final ArrayList<Poetry> ret = new ArrayList<Poetry>();
		for ( final Today t : poetries.keySet() )
		{
			ret.add( poetries.get( t ) );
		}
		
		return ret;
	}

	public
	List<Poetry>
	getPoetiesOf(
		final String poetId,
		final String start
	)
	{
		if ( null == start )
		{
			return addDetailInformation( poetryDao.getPoetryOf( poetId ), null );
		}
		else
		{
			return addDetailInformation( poetryDao.getPoetryOf( poetId, start ), null );
		}
	}

	public
	Poetry
	getPoetry(
		final String poetryId,
		final String username
	)
	{
		return addDetailInformation( poetryDao.getPoetry( poetryId ), username );

	}
	
	public
	int
	addStar(
		final String poetryId
	)
	{
		Assert.isTrue( SignUtils.isSignIn() );
		final Poetry poetry = poetryDao.get( Poetry.class, poetryId );
		if ( ObjectUtils.equals( SignUtils.getSignedInUsername(), poetry.getAuthor().getUsername() ) )
		{
			throw new IllegalArgumentException( "자신의 시에 별을 줄 수 없습니다." );
		}
		
		starDao.addStar( new Star( poetryId, SignUtils.getSignedInUsername() ) );
		return starDao.getTheNumberOfStar( poetryId );
	}
	
	public
	int
	removeStar(
		final String poetryId
	)
	{
		Assert.isTrue( SignUtils.isSignIn() );
		starDao.removeStar( new Star( poetryId, SignUtils.getSignedInUsername() ) );
		return starDao.getTheNumberOfStar( poetryId );
	}

	public
	int
	addBookmark(
		final String poetryId
	)
	{
		Assert.isTrue( SignUtils.isSignIn() );
		final Poetry poetry = poetryDao.get( Poetry.class, poetryId );
		if ( ObjectUtils.equals( SignUtils.getSignedInUsername(), poetry.getAuthor().getUsername() ) )
		{
			throw new IllegalArgumentException( "자신의 시를 북마크할 수 없습니다." );
		}
		bookmarkDao.addBookmark( new Bookmark( poetryId, SignUtils.getSignedInUsername() ) );
		return bookmarkDao.getTheNumberOfBookmarks( poetryId );
	}

	public
	int
	removeBookmark( String poetryId )
	{
		Assert.isTrue( SignUtils.isSignIn() );
		bookmarkDao.removeBookmark( new Bookmark( poetryId, SignUtils.getSignedInUsername() ) );
		return bookmarkDao.getTheNumberOfBookmarks( poetryId );

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
		if ( null == start ) {
			return todayDao.getCandidates();
		}
		else
		{
			return todayDao.getCandidates( start );
		}
	}

	public List<Poetry>
	getBookmarksOf(
		final String poetId,
		final String start
	)
	{
		if ( null == start )
		{
			return addDetailInformation( bookmarkDao.getBookmarksOf( poetId ), null );
		}
		else 
		{
			return addDetailInformation( bookmarkDao.getBookmarksOf( poetId, start ), null );
			
		}
	}
	

	public
	void
	addReply(
		final Reply reply
	)
	{
		Poetry poetry = poetryDao.getPoetry( reply.getTargetId() );
		if ( blockDao.exists( Block.class, new Block( poetry.getAuthor().getUsername(), reply.getWriter().getUsername() ) ) )
		{
			throw new IllegalArgumentException();
		}
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
		logger.trace( "Trying list reply for {} from {}", targetId, start );
		if ( null == start )
		{
			return replyDao.list( targetId );
		}
		else
		{
			return replyDao.list( targetId, start );
		}
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
