package com.poetry.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.poetry.dao.BinaryDao;
import com.poetry.model.Binary;

public class
BinaryService
{
	
	@Autowired
	protected BinaryDao binaryDao;
	
	public Binary getBinary( final String id )
	{
		return binaryDao.get( id );
	}

	public String upload( final Binary binary )
	{
		return binaryDao.insert( binary );
		
	}

}
