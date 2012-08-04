package com.poetry.service;

import java.util.ArrayList;
import java.util.List;

import com.poetry.model.Poet;
import com.poetry.model.Poetry;
import com.poetry.model.PoetryStatus;
import com.poetry.model.Today;

public class
PoetryServiceMock
implements PoetryService
{
	@Override
	public
	Poetry
	getPoetry(
		final String id
	)
	{
		if ( "poetry1".equals( id ) )
		{
			final Poetry poetry1 = new Poetry();
			poetry1.setId( "poetry1" );
			poetry1.setTitle( "Into the sky" );
			poetry1.setAuthor( new Poet( "AJ", "안종현" ) );
			poetry1.setContents( "dive into the sky. blah blah" );
			poetry1.setImage( "image1" );
			
			return poetry1;
		}
		else if ( "poetry2".equals( id ) )
		{
			
			final Poetry poetry2 = new Poetry();
			poetry2.setId( "poetry2" );
			poetry2.setTitle( "Without you" );
			poetry2.setAuthor( new Poet( "Park", "박충순" ) );
			poetry2.setContents( "Life is alone ~" );
			poetry2.setImage( "Image2" );
			return poetry2;
		}
		
		else if ( "poetry3".equals( id ) )
		{
			final Poetry poetry3 = new Poetry();
			poetry3.setId( "poetry3" );
			poetry3.setTitle( "Big picture..." );
			poetry3.setAuthor( new Poet( "Lee", "이본용" ) );
			poetry3.setContents( "Open your ..." );
			poetry3.setImage( "image3" );
			return poetry3;
			
		}
		else if ( "poetry4".equals( id ) )
		{
			
			final Poetry poetry4 = new Poetry();
			poetry4.setId( "poetry4" );
			poetry4.setTitle( "Rule the sky" );
			poetry4.setAuthor( new Poet( "Sung", "성한승" ) );
			poetry4.setContents( "Life is alone ~" );
			poetry4.setImage( "image4" );
			return poetry4;
		}
		throw new IllegalArgumentException();
	}
	@Override
	public List<Poetry> getTodayPoetries()
	{
		ArrayList<Poetry> ret = new ArrayList<Poetry>();
		final Poetry poetry1 = new Poetry();
		poetry1.setId( "poetry1" );
		poetry1.setTitle( "Into the sky" );
		poetry1.setAuthor( new Poet( "AJ", "안종현" ) );
		poetry1.setContents( "dive into the sky. blah blah" );
		poetry1.setImage( "image1" );
		ret.add( poetry1 );


		final Poetry poetry2 = new Poetry();
		poetry2.setId( "poetry2" );
		poetry2.setTitle( "Without you" );
		poetry2.setAuthor( new Poet( "Park", "박충순" ) );
		poetry2.setContents( "Life is alone ~" );
		poetry2.setImage( "Image2" );
		ret.add( poetry2 );
		
		return ret;
	}
	
	@Override
	public Poetry add( Poetry poetry )
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void addStar( String poetryId )
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void removeStar( String poetryId )
	{
		throw new UnsupportedOperationException();
	}
	public PoetryStatus getPoetryStatus( String poetryId )
	{
		throw new UnsupportedOperationException();
	}
	public void addBookmark( String poetryId )
	{
		throw new UnsupportedOperationException();
	}
	public void removeBookmark( String poetryId )
	{
		throw new UnsupportedOperationException();
	}
	public void addFollowing( String poetId )
	{
		throw new UnsupportedOperationException();
	}
	public void removeFollowing( String poetId )
	{
		throw new UnsupportedOperationException();
	}
	public void setTodayPoetry( Today today )
	{
		throw new UnsupportedOperationException();
	}
	public List<Poetry> getTodayCandidates( String start )
	{
		throw new UnsupportedOperationException();
	}

}
