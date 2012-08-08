package com.poetry.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.poetry.model.Following;
import com.poetry.model.Poet;

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
	
	@SuppressWarnings("unchecked")
	public
	List<Poet>
	getFollowings(
		final String poetId
	)
	{
		logger.trace( "Trying get followings for {}", poetId );
		return extract( (List<Object[]>) find(
			"from Poet poet, Following following " +
			"where poet.username = following.following and following.follower = ?",
			poetId
		), 0 );
	}
	
	@SuppressWarnings("unchecked")
	public
	List<Poet>
	getUnblockedFollowers(
		final String followingId
	)
	{
		logger.trace( "Trying get followings for {}", followingId );
			
		return extract( (List<Object[]>) find(
			"from Following f " +
			"where f.following = ? and  ( f.following, f.follower ) not in ( select b.following, b.follower from Block b )",
			followingId
		), 0 );
	}

	@SuppressWarnings("unchecked")
	public
	List<Poet>
	getFollowers(
		final String poetId
	)
	{
		logger.trace( "Trying get followers for {}", poetId );
		return extract( (List<Object[]>) find(
			"from Poet poet, Following following " +
			"where poet.username = following.follower and following.following = ?",
			poetId
		), 0 );
	}
	
}
