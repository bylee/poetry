package com.poetry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poetry.dao.BinaryDao;
import com.poetry.dao.PoetDao;
import com.poetry.dao.PoetryDao;
import com.poetry.model.Poet;

@Service
public class
PoetServiceImpl
implements UserDetailsService, PoetService
{
	
	protected final Logger logger = LoggerFactory.getLogger( getClass() );
	
	@Autowired
	protected PoetDao poetDao;
	
	@Autowired
	protected BinaryDao binaryDao;
	
	@Autowired
	protected PoetryDao poetryDao;

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

	@Override
	public
	Poet
	getPoetDetail( final String username )
	{
		final Poet poet = poetDao.getPoet( username );
		poet.setTheNumberOfPoetries( poetryDao.getTheNumberOfPoetries( username ) );
		poet.setTheNumberOfFollowers( poetDao.getTheNumberOfFollowers( username ) );
		poet.setTheNumberOfFollowings( poetDao.getTheNumberOfFollowings( username ) );
		poet.setTheNumberOfClips( binaryDao.getTheNumberOfClips( username ) );
		
		return poet;
	}

}
