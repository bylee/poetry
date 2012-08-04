package com.poetry.service;

import java.util.List;

import com.poetry.model.Poetry;
import com.poetry.model.Today;

public interface PoetryService
{
	Poetry addPoetry( final Poetry poetry );

	Poetry addMissionPoetry( final Poetry poetry );


	List<Poetry> getTodayPoetries();

	Poetry getPoetry( final String poetryId );
	
	void addStar( final String poetryId );

	void removeStar( final String poetryId );
	
	void addBookmark( final String poetryId );
	
	void removeBookmark( final String poetryId );
	
	void addFollowing( final String poetId );
	
	void removeFollowing( final String poetId );

	void setTodayPoetry( Today today );

	List<Poetry> getTodayCandidates( String start );

	List<Poetry> getPoetiesOf( String poetId );

	List<Poetry> getBookmarksOf( String poetId );

}
