package com.poetry.dao;

import org.springframework.stereotype.Repository;

import com.poetry.model.Poet;

@Repository
public class
UserDao
extends AbstractDao
{
	public void addNewPoet(
		final Poet user
	)
	{
		insert( user );
	}
	public Poet getPoet(
		final String username
	)
	{
		return get( Poet.class, username );
	}
	
	public
	Poet
	getUser(
		final String username
	)
	{
		return get( Poet.class, username );
	}

}
