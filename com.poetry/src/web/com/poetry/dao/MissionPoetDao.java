package com.poetry.dao;

import org.springframework.stereotype.Repository;

import com.poetry.model.MissionPoet;

@Repository
public class
MissionPoetDao
extends AbstractDao
{
	
	public void addMissionPoet( MissionPoet missionPoet )
	{
		missionPoet.setId( generateId( missionPoet ) );
		insert( missionPoet );
	}
	
}
