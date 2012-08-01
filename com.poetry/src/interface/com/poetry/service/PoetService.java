package com.poetry.service;

import com.poetry.model.Poet;

public interface PoetService
{
	void addNewPoet( Poet poet );
	
	Poet getPoetDetail( String username );


}
