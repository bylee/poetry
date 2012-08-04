package com.poetry.dao;

import org.springframework.stereotype.Repository;

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
	
}
