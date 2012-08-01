package com.poetry.dao;

import org.springframework.stereotype.Repository;

import com.poetry.model.Bookmark;

@Repository
public class
BookmarkDao
extends AbstractDao
{
	public
	boolean
	exists(
		final String poetryId,
		final String poetId
	)
	{
		return exists( new Bookmark( poetryId, poetId ) );
	}
	
	public
	boolean
	exists(
		final Bookmark bookmark
	)
	{
		getSession().flush();
		return exists( Bookmark.class, bookmark );
	}

	public
	void
	addBookmark(
		final Bookmark bookmark
	)
	{
		insert( bookmark );
	}
	
	public
	void
	removeBookmark(
		final Bookmark bookmark
	)
	{
		delete( bookmark );
	}
}
