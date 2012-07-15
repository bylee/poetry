package com.poetry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import com.poetry.dao.PoetDao;

public class
PoetService
implements UserDetailsManager
{
	
	protected final Logger logger = LoggerFactory.getLogger( getClass() );
	
	@Autowired
	protected PoetDao poetDao;

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
			userDetails = poetDao.getPoet( username );
			
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

	public void createUser( UserDetails userDetails )
	{
	}

	public void updateUser( UserDetails userDetails )
	{
	}

	public void deleteUser( String username )
	{
		// TODO Auto-generated method stub
		
	}

	public void changePassword( String username, String password )
	{
		// TODO Auto-generated method stub
		
	}

	public boolean userExists( String username )
	{
		return false;
	}

}
