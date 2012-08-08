package com.poetry.dao;

import org.springframework.stereotype.Repository;

import com.poetry.model.Binary;

@Repository
public class
BinaryDao
extends AbstractDao
{
	public
	String
	addNewBinary(
		final Binary binary
	)
	{
		binary.setId( generateId( binary ) );
		super.insert( binary );
		
		return binary.getId();
	}
	public
	Binary
	get(
		final String id
	)
	{
		return get( Binary.class, id );
	}
	
}
