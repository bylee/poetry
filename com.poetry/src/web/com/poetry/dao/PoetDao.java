package com.poetry.dao;

import java.text.MessageFormat;

import org.springframework.stereotype.Repository;

import com.poetry.model.Following;
import com.poetry.model.Poet;

@Repository
public class
PoetDao
extends AbstractDao
{
	public void addNewPoet(
		final Poet user
	)
	{
		insert( user );
	}
	public Poet getPoet(
		final String username
	)
	{
		return get( Poet.class, username );
	}
	
	public
	Poet
	getUser(
		final String username
	)
	{
		return get( Poet.class, username );
	}
	
	public
	void
	addFollowing( final Following following )
	{
		insert( following );
	}
	
	public
	int
	getTheNumberOfFollowings(
		final String username
	)
	{
		final String query = 
			MessageFormat.format( "select count(following.follower) from Following following where following.follower = ''{0}''" , username );
		return ( (Long) getSession().createQuery( query ).uniqueResult() ).intValue();
	}

	public
	int
	getTheNumberOfFollowers(
		final String username
	)
	{
		final String query = 
			MessageFormat.format( "select count(following.following) from Following following where following.following = ''{0}''" , username );
		return ( (Long) getSession().createQuery( query ).uniqueResult() ).intValue();
	}

}
