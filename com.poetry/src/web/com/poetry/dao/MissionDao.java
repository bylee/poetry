package com.poetry.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.poetry.model.Mission;

@Repository
public class MissionDao
extends AbstractDao
{
	public
	void
	addNewMission( final Mission mission )
	{
		mission.setId( generateId( mission ) );
		delete( "delete from Mission mission where mission.date = ?", mission.getDate() );
		insert( mission );
	}
	public
	Mission
	getMission(
		final Date date
	)
	{
		return findOne( "from Mission mission where mission.date = ?", date );
	}

}
