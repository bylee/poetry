package com.poetry.dao;

import org.springframework.stereotype.Repository;

import com.poetry.model.User;

@Repository
public class
PoetDao
extends AbstractDao
{
	public User getPoet(
		final String username
	)
	{
		return get( User.class, username );
	}

}
