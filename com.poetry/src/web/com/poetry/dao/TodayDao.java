package com.poetry.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.poetry.model.Poetry;
import com.poetry.model.Today;
import com.poetry.util.DateUtils;

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
		return (List<Poetry>) find(
			"from Poetry p " +
			"where p.createdDate = ? " +
			"order by p.id",
			DateUtils.getYesterday()
		);
	}
	
	@SuppressWarnings("unchecked")
	public List<Poetry> getCandidates( final String start )
	{
		return (List<Poetry>) find(
			"from Poetry p " +
			"where p.createdDate = ? and p.id < ? " +
			"order by p.id desc",
			DateUtils.getYesterday(), start
		);
	}
	
}
