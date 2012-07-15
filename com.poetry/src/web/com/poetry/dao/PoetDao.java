package com.poetry.dao;

import org.springframework.stereotype.Repository;

import com.poetry.model.Poet;

@Repository
public class
PoetDao
extends AbstractDao
{
	public Poet getPoet(
		final String username
	)
	{
		return get( Poet.class, username );
	}

}
