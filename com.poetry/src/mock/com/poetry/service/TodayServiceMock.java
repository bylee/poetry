package com.poetry.service;

import java.util.ArrayList;
import java.util.List;

import com.poetry.model.Poet;
import com.poetry.model.Poetry;

public class
TodayServiceMock
extends TodayService
{
	@Override
	public List<Poetry> getTodayPoetries( String startId )
	{
		logger.trace( "Start ID :{}", startId );
		ArrayList<Poetry> ret = new ArrayList<Poetry>();
		if ( null == startId )
		{
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
		}
		else if ( "poetry2".equals( startId ) )
		{
			final Poetry poetry3 = new Poetry();
			poetry3.setId( "poetry3" );
			poetry3.setTitle( "Big picture..." );
			poetry3.setAuthor( new Poet( "Lee", "이본용" ) );
			poetry3.setContents( "Open your ..." );
			poetry3.setImage( "image3" );
			ret.add( poetry3 );
			
			
			final Poetry poetry4 = new Poetry();
			poetry4.setId( "poetry4" );
			poetry4.setTitle( "Rule the sky" );
			poetry4.setAuthor( new Poet( "Sung", "성한승" ) );
			poetry4.setContents( "Life is alone ~" );
			poetry4.setImage( "image4" );
			ret.add( poetry4 );
		}
		
		return ret;
	}

}
