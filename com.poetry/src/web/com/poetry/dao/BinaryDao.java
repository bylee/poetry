package com.poetry.dao;

import java.text.MessageFormat;

import org.springframework.stereotype.Repository;

import com.poetry.model.Binary;

@Repository
public class
BinaryDao
extends AbstractDao
{
	public String
	addBinary(
		final Binary binary
	)
	{
		binary.setId( generateId( binary ) );
		super.insert( binary );
		
		return binary.getId();
	}
	public Binary
	get( final String id )
	{
		return get( Binary.class, id );
	}
	
	public int getTheNumberOfClips( String username )
	{
		final String query = 
			MessageFormat.format( "select count(binary.owner) from Binary binary where binary.owner = ''{0}''" , username );
		return ( (Long) getSession().createQuery( query ).uniqueResult() ).intValue();
	}

}
