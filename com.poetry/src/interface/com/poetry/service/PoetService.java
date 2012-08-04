package com.poetry.service;

import java.util.List;

import com.poetry.model.Poet;

public interface PoetService
{
	void addNewPoet( Poet poet );
	
	Poet getPoetDetail( String username );
	
	List<Poet> getFollowings( final String poetId );

	List<Poet> getFollowers( final String poetId );

}
