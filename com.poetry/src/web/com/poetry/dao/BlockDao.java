package com.poetry.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.poetry.model.Block;
import com.poetry.model.Poet;

@Repository
public class
BlockDao
extends AbstractDao
{
	public
	void
	addBlock(
		final Block block
	)
	{
		insert( block );
	}
	
	public
	void
	removeBlock(
		final Block block
	)
	{
		delete( block );
	}
	
	@SuppressWarnings("unchecked")
	public
	List<Poet>
	getBlockedFollowers(
		final String followingId
	)
	{
		logger.trace( "Trying get followings for {}", followingId );
			
		return extract( (List<Object[]>) find(
			"from Poet p, Block f " +
			"where p.id = f.follower and f.following = ?",
			followingId
		), 0 );
	}
	
}
