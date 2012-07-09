package com.poetry.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.poetry.dao.PoetryDao;
import com.poetry.model.Poetry;

public class
PoetryService
{
	@Autowired
	protected PoetryDao poetryDao;
	
	public Poetry getPoetry( final String id )
	{
		return poetryDao.get( id );
	}


	public Poetry add( final Poetry poetry )
	{
		poetryDao.insert( poetry );
		return poetry;
	}

}
