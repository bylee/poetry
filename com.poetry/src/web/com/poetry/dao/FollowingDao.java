package com.poetry.dao;

import org.springframework.stereotype.Repository;

import com.poetry.model.Following;

@Repository
public class
FollowingDao
extends AbstractDao
{
	public
	boolean
	exists(
		final String following,
		final String follower
	)
	{
		return exists( new Following( following, follower ) );
	}
	
	public
	boolean
	exists(
		final Following following
	)
	{
		return exists( Following.class, following );
	}

	public
	void
	addFollowing(
		final Following following
	)
	{
		insert( following );
	}
	
	public
	void
	removeFollowing(
		final Following following
	)
	{
		delete( following );
	}
}
