package com.poetry.service;

import java.util.List;

import com.poetry.model.Poetry;

public interface PoetryService
{

	Poetry add( final Poetry poetry );

	List<Poetry> getTodayPoetries( String date );

	Poetry getPoetry( final String poetId );

	void addStar( final String poetryId );

	void removeStar( final String poetryId );

}
