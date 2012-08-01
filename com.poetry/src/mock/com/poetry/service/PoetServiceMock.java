package com.poetry.service;

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

}
