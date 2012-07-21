package com.poetry.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.poetry.dao.BinaryDao;
import com.poetry.model.Binary;

public class
BinaryServiceImpl
implements BinaryService
{
	
	@Autowired
	protected BinaryDao binaryDao;
	
	@Override
	public
	Binary
	getBinary(
		final String id
	)
	{
		return binaryDao.get( id );
	}

	@Override
	public
	String
	upload(
		final Binary binary
	)
	{
		return binaryDao.addBinary( binary );
		
	}

}
