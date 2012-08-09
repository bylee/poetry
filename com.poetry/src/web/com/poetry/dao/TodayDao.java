package com.poetry.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poetry.model.Poetry;
import com.poetry.model.Today;

@Repository
public class
TodayDao
extends AbstractDao
{
	public void setToday( Today today )
	{
		delete( "delete from Today today where today.date = ?", today.getDate() );
		insert( today );
	}
	@SuppressWarnings("unchecked")
	public List<Poetry> getCandidates()
	{
		final Date yesterday = new Date( System.currentTimeMillis() - 24 * 60 * 60 * 1000 );
		return (List<Poetry>) find(
			"from Poetry p " +
			"where p.createdDate = ? " +
			"order by p.id",
			yesterday
		);
	}
	
	@SuppressWarnings("unchecked")
	public List<Poetry> getCandidates( final String start )
	{
		final Date yesterday = new Date( System.currentTimeMillis() - 24 * 60 * 60 * 1000 );
		return (List<Poetry>) find(
			"from Poetry p " +
			"where p.createdDate = ? and p.id < ? " +
			"order by p.id desc",
			yesterday, start
		);
	}
	
}
