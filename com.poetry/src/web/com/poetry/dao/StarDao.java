package com.poetry.dao;

import java.text.MessageFormat;

import org.springframework.stereotype.Repository;

import com.poetry.model.Star;

@Repository
public class
StarDao
extends AbstractDao
{
	public int getTheNumberOfStar( final String poetId )
	{
		getSession().flush();
		final String query = 
			MessageFormat.format( "select count(star.poetId) from Star star where star.poetId = ''{0}''" , poetId );
		return ( (Long) getSession().createQuery( query ).uniqueResult() ).intValue();
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
