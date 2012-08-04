package com.poetry.dao;

import java.text.MessageFormat;
import java.util.ArrayList;
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
		final String query = 
			MessageFormat.format( "select count( bookmark.poetId ) from Bookmark bookmark where bookmark.poetId = ''{0}''" , username );
		return ( (Long) getSession().createQuery( query ).uniqueResult() ).intValue();

	}

	@SuppressWarnings("unchecked")
	public List<Poetry> getBookmarksOf( String poetId )
	{
		final List<Object[]> list =
			(List<Object[]>) find(  "from Poetry poetry, Bookmark bookmark where poetry.id = bookmark.poetryId and bookmark.poetId = ?", poetId );
		
		ArrayList<Poetry> poetries = new ArrayList<Poetry>();
		for ( final Object[] row : list )
		{
			poetries.add( (Poetry) row[0] );
		}
		
		return poetries;
	}

}
