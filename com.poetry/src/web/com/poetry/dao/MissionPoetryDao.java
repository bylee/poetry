package com.poetry.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poetry.model.MissionPoetry;
import com.poetry.model.Poetry;

@Repository
public class
MissionPoetryDao
extends AbstractDao
{
	
	public void addMissionPoetry( MissionPoetry missionPoetry )
	{
		missionPoetry.setId( generateId( missionPoetry ) );
		insert( missionPoetry );
	}
	
	@SuppressWarnings("unchecked")
	public
	List<Poetry>
	getMissionPoetry(
		final Date date
	)
	{
		return extract(
			(List<Object[]>) find(
				"from Poetry p, MissionPoetry mp " +
				"where p.id = mp.poetryId and mp.date = ? order by p.id",
				date
			),
			0
		);
	}

	@SuppressWarnings("unchecked")
	public
	List<Poetry>
	getMissionPoetry(
		final Date date,
		final String start
	)
	{
		return extract(
			(List<Object[]>) find(
				"from Poetry p, MissionPoetry mp " +
				"where p.id = mp.poetryId and mp.date = ? order by p.id",
				date
			),
			0
		);
	}

}
