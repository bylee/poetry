package com.poetry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poetry.dao.BinaryDao;
import com.poetry.dao.BookmarkDao;
import com.poetry.dao.FollowingDao;
import com.poetry.dao.PoetDao;
import com.poetry.dao.PoetryDao;
import com.poetry.dao.ReplyDao;
import com.poetry.model.Poet;

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
		poet.setTheNumberOfPoetries( poetryDao.getTheNumberOfPoetries( username ) );
		poet.setTheNumberOfFollowers( poetDao.getTheNumberOfFollowers( username ) );
		poet.setTheNumberOfFollowings( poetDao.getTheNumberOfFollowings( username ) );
		poet.setTheNumberOfBookmarks( bookmarkDao.getTheNumberOfBookmarks( username ) );
		
		return poet;
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
		return followingDao.getFollowers( poetId );
	}


}
