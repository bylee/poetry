package com.poetry.dao;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poetry.model.Poetry;


@Repository
public class
PoetryDao
extends AbstractDao
{
	public void addPoetry( final Poetry poetry )
	{
		poetry.setId( generateId( poetry ) );
		super.insert( poetry );
	}

	@SuppressWarnings("unchecked")
	public List<Poetry> listPoetry()
	{
		final String query =
			"from Poetry poetry order by poetry";
		
		return (List<Poetry>) find( query ); 
	}

	@SuppressWarnings("unchecked")
	public List<Poetry> listPoetryAfter( final String startId )
	{
		final String query =
			MessageFormat.format( "from Poetry poetry where poetry.id < '{0}' order by poetry.id", startId );
		
		return (List<Poetry>) find( query ); 
	}
	

	public Poetry getPoetry( final String id )
	{
		final Poetry poetry = get( Poetry.class, id );
		
		if ( null == poetry )
		{
			return null;
		}
		
		return poetry;
	}
	
	public
	int
	getTheNumberOfPoetries(
		final String username
	)
	{
		final String query = 
			MessageFormat.format( "select count(poetry.author) from Poetry poetry where poetry.author = ''{0}''" , username );
		return ( (Long) getSession().createQuery( query ).uniqueResult() ).intValue();
	}

}
