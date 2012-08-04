package com.poetry.service;

import java.util.Date;

import com.poetry.model.Binary;
import com.poetry.model.Mission;
import com.poetry.model.MissionPoet;

public interface
BinaryService
{
	Mission getMission();
	
	Mission getMission( Date date );
	
	Binary getBinary( final String id );

	String upload( final Binary binary );
	
	String upload( final Mission mission );
	
	void upload( final MissionPoet missionPoet );

}
