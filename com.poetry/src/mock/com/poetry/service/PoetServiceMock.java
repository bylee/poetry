package com.poetry.service;

import java.util.List;

import com.poetry.model.Poet;

public class
PoetServiceMock
implements PoetService
{

	@Override
	public Poet getPoetDetail( String username )
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void addNewPoet(Poet poet) {
		
	}

	public List<Poet> getFollowings( String poetId )
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<Poet> getFollowers( String poetId )
	{
		// TODO Auto-generated method stub
		return null;
	}

}
