package com.poetry.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.poetry.dao.PoetryDao;
import com.poetry.model.Poetry;

public class
TodayService
{
	protected final Logger logger = LoggerFactory.getLogger( getClass() );
	
	@Autowired
	protected PoetryDao poetryDao;
	
	public
	List<Poetry>
	getTodayPoetries(
		final String date
	)
	{
		if ( null == date )
		{
			return poetryDao.list();
		}
		else
		{
			return poetryDao.listAfter( date );
		}
		
	}

}
