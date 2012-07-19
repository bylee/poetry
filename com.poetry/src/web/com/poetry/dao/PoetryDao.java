package com.poetry.dao;

import static com.poetry.util.PoetryUtils.strip;

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
		
		return strip( (List<Poetry>) find( query ) ); 
	}

	@SuppressWarnings("unchecked")
	public List<Poetry> listPoetryAfter( final String startId )
	{
		final String query =
			MessageFormat.format( "from Poetry poetry where poetry.id < '{0}' order by poetry.id", startId );
		
		return strip( (List<Poetry>) find( query ) ); 
	}

	public Poetry getPoetry( final String id )
	{
		final Poetry poetry = get( Poetry.class, id );
		
		if ( null == poetry )
		{
			return null;
		}
		
		return strip( poetry )[0];
	}

}
