package com.poetry.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poetry.dao.BinaryDao;
import com.poetry.dao.BlockDao;
import com.poetry.dao.BookmarkDao;
import com.poetry.dao.FollowingDao;
import com.poetry.dao.PoetDao;
import com.poetry.dao.PoetryDao;
import com.poetry.dao.ReplyDao;
import com.poetry.model.Block;
import com.poetry.model.Following;
import com.poetry.model.Poet;
import com.poetry.util.SignUtils;

import escode.util.Assert;
import escode.util.ObjectUtils;

@Service
public class
PoetService
extends AbstractService
implements UserDetailsService
{
	
	@Autowired( required = false )
	protected PoetDao poetDao;
	
	@Autowired( required = false )
	protected FollowingDao followingDao;
	
	@Autowired( required = false )
	protected BlockDao blockDao;
	
	@Autowired( required = false )
	protected BinaryDao binaryDao;
	
	@Autowired( required = false )
	protected PoetryDao poetryDao;
	
	@Autowired( required = false )
	protected ReplyDao replyDao;
	
	@Autowired( required = false )
	protected BookmarkDao bookmarkDao;
	
	public void
	addNewPoet(
		final Poet poet
	)
	{
		poetDao.addNewPoet( poet );
	}

	@Override
	public
	UserDetails
	loadUserByUsername(
		final String username
	)
	throws UsernameNotFoundException
	{
		UserDetails userDetails = null;
		try
		{
			userDetails = poetDao.getUser( username );
			
			logger.trace( "user info :{}", userDetails );
			
			if ( null == userDetails )
			{
				throw new UsernameNotFoundException( "Invalid username" );
			}
		}
		catch ( final Throwable e )
		{
			e.printStackTrace();
			
			throw new UsernameNotFoundException( "Invalid username :" + username );
		}
		
		return userDetails;
	}

	public
	Poet
	getPoetDetail(
		final String username
	)
	{
		final Poet poet = poetDao.getPoet( username );
		return getPoetDetail( poet );
	}
	
	public
	Poet
	getPoetDetail(
		final Poet poet
	) {
		final String username = poet.getUsername();
		poet.setTheNumberOfPoetries( poetryDao.getTheNumberOfPoetries( username ) );
		poet.setTheNumberOfFollowers( poetDao.getTheNumberOfFollowers( username ) );
		poet.setTheNumberOfFollowings( poetDao.getTheNumberOfFollowings( username ) );
		poet.setTheNumberOfBookmarks( bookmarkDao.getTheNumberOfBookmarks( username ) );
		poet.setBlock( blockDao.exists( Block.class, new Block( SignUtils.getSignedInUsername(), username ) ) );
		
		return poet;
	}
	
	public
	List<Poet>
	getPoetDetail(
		final List<Poet> poets
	) {
		for ( Poet poet : poets )
		{
			getPoetDetail( poet );
		}
		
		return poets;
	}
	

	public
	List<Poet>
	getFollowings(
		final String poetId
	)
	{
		return followingDao.getFollowings( poetId );
	}

	public
	List<Poet>
	getFollowers(
		final String poetId
	)
	{
		return getPoetDetail( followingDao.getFollowers( poetId ) );
	}

	public 
	void
	addFollowing( String poetId )
	{
		Assert.isTrue( SignUtils.isSignIn() );
		if ( ObjectUtils.equals( poetId, SignUtils.getSignedInUsername() ) )
		{
			throw new IllegalArgumentException( "자신을 팔로윙할 수 없습니다." );
		}
		followingDao.addFollowing( new Following( poetId, SignUtils.getSignedInUsername() ) );
	}

	public 
	void
	removeFollowing( String poetId )
	{
		Assert.isTrue( SignUtils.isSignIn() );
		followingDao.removeFollowing( new Following( poetId, SignUtils.getSignedInUsername() ) );
	}

	public
	void
	addBlock( final String poetId )
	{
		Assert.isTrue( SignUtils.isSignIn() );
		blockDao.addBlock( new Block( SignUtils.getSignedInUsername(), poetId ) );
	}
	
	public
	void
	removeBlock( final String poetId )
	{
		Assert.isTrue( SignUtils.isSignIn() );
		blockDao.removeBlock( new Block( SignUtils.getSignedInUsername(), poetId ) );
	}

	@SuppressWarnings("unchecked")
	public
	List<Poet>
	getBlocks( final String username )
	{
		if ( !ObjectUtils.equals( username, SignUtils.getSignedInUsername() ) )
		{
			return Collections.EMPTY_LIST;
		}
		return getPoetDetail( blockDao.getBlockedFollowers( username ) );
	}


}
