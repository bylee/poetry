package com.poetry.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.poetry.model.Star;

@Repository
public class
StarDao
extends AbstractDao
{
	public int getTheNumberOfStar( final String poetryId )
	{
		getSession().flush();
		Query q = getSession().createQuery( "select count(star.poetId) from Star star where star.poetryId = ?" );
		bind( q, poetryId );
		return ( (Long) q.uniqueResult() ).intValue();
	}
	
	public
	boolean
	exists(
		final String poetryId,
		final String poetId
	)
	{
		return exists( new Star( poetryId, poetId ) );
	}
	
	public
	boolean
	exists(
		final Star star
	)
	{
		return exists( Star.class, star );
	}

	public
	void
	addStar(
		final Star star
	)
	{
		insert( star );
	}
	
	public
	void
	removeStar(
		final Star star
	)
	{
		delete( star );
	}

}
