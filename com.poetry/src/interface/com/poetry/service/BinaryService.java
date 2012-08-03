package com.poetry.service;

import java.util.Date;

import com.poetry.model.Binary;
import com.poetry.model.Mission;

public interface
BinaryService
{
	Mission getMission();
	
	Mission getMission( Date date );
	
	Binary getBinary( final String id );

	String upload( final Binary binary );
	
	String upload( final Mission mission );

}
