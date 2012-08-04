package com.poetry.dao;

import org.springframework.stereotype.Repository;

import com.poetry.model.MissionPoetry;

@Repository
public class
MissionPoetDao
extends AbstractDao
{
	
	public void addMissionPoet( MissionPoetry missionPoet )
	{
		missionPoet.setId( generateId( missionPoet ) );
		insert( missionPoet );
	}
	
}
