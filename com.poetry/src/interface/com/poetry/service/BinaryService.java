package com.poetry.service;

import com.poetry.model.Binary;

public interface BinaryService
{
	Binary getBinary( final String id );

	String upload( final Binary binary );

}
