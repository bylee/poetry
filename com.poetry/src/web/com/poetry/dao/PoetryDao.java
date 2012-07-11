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
	public void insert( final Poetry poetry )
	{
		poetry.setId( generateId( poetry ) );
		super.insert( poetry );
	}

	public List<Poetry> list()
	{
		final String query =
			"from Poetry poetry order by poetry";
		
		return (List<Poetry>) find( query ); 
	}

	public List<Poetry> listAfter( final String startId )
	{
		final String query =
			MessageFormat.format( "from Poetry poetry where poetry.id < '{0}' order by poetry.id", startId );
		
		return (List<Poetry>) find( query ); 
	}

	public Poetry get( final String id )
	{
		return get( Poetry.class, id );
	}

}
