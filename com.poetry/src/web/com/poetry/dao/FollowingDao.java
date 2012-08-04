package com.poetry.dao;

import java.util.ArrayList;
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
	
	public
	List<Poet>
	getFollowings( final String poetId )
	{
		final List<Object[]> list =
			(List<Object[]>) find( "from Poet poet, Following following where poet.username = following.following and following.follower = ?", poetId );
		
		final ArrayList<Poet> poets = new ArrayList<Poet>();
		
		for ( final Object[] objs : list )
		{
			poets.add( (Poet) objs[0] );
		}
		return poets;
	}
	
	public
	List<Poet>
	getFollowers( final String poetId )
	{
		final List<Object[]> list =
			(List<Object[]>) find( "from Poet poet, Following following where poet.username = following.follower and following.following = ?", poetId );
		
		final ArrayList<Poet> poets = new ArrayList<Poet>();
		
		for ( final Object[] objs : list )
		{
			poets.add( (Poet) objs[0] );
		}
		return poets;
	}
	
}
