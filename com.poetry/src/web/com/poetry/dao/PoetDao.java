package com.poetry.dao;

import org.springframework.stereotype.Repository;

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
	int
	getTheNumberOfFollowings(
		final String username
	)
	{
		return ( (Long) findOne(
			"select count(following.follower) " +
			"from Following following " +
			"where following.follower = ?",
			username
		) ).intValue();
	}

	public
	int
	getTheNumberOfFollowers(
		final String username
	)
	{
		return ( (Long) findOne(
			"select count(following.following) " +
			"from Following following " +
			"where following.following = ?" ,
			username
		) ).intValue();
	}

}
