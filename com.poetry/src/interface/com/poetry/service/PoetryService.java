package com.poetry.service;

import java.util.List;

import com.poetry.model.Poetry;
import com.poetry.model.PoetryStatus;

public interface PoetryService
{

	Poetry add( final Poetry poetry );

	List<Poetry> getTodayPoetries( String date );

	Poetry getPoetry( final String poetryId );
	
	PoetryStatus getPoetryStatus( final String poetryId );

	void addStar( final String poetryId );

	void removeStar( final String poetryId );
	
	void addBookmark( final String poetryId );
	
	void removeBookmark( final String poetryId );
	
	void addFollowing( final String poetId );
	
	void removeFollowing( final String poetId );

}
