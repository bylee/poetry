package com.poetry.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.poetry.model.Bookmark;
import com.poetry.model.Poetry;

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
	
	public
	int
	getTheNumberOfBookmarks( final String username )
	{
		logger.trace( "trying the number of {}'s bookmark", username );
		return ( (Long) findOne(
			"select count( bookmark.poetId ) " +
			"from Bookmark bookmark " +
			"where bookmark.poetId = ?",
			username
		) ).intValue();

	}

	@SuppressWarnings("unchecked")
	public List<Poetry> getBookmarksOf( String poetId )
	{
		return extract( (List<Object[]>) find(
			"from Poetry poetry, Bookmark bookmark " +
			"where poetry.id = bookmark.poetryId and bookmark.poetId = ? " +
			"order by poetry.id desc",
			poetId
		), 0 );
	}
	
	@SuppressWarnings("unchecked")
	public List<Poetry> getBookmarksOf(
		final String poetId,
		final String start
	)
	{
		return extract( (List<Object[]>) find(
			"from Poetry poetry, Bookmark bookmark " +
			"where poetry.id = bookmark.poetryId and bookmark.poetId = ? and poetry.id < ?" +
			"order by poetry.id desc",
			poetId, start
		), 0 );
	}

}
