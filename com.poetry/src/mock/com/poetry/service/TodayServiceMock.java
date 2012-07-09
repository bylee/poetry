package com.poetry.service;

import java.util.ArrayList;
import java.util.List;

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
			poetry1.setAuthor( "AJ" );
			poetry1.setContents( "dive into the sky. blah blah" );
			poetry1.setImageId( "image1" );
			ret.add( poetry1 );
			
			
			final Poetry poetry2 = new Poetry();
			poetry2.setId( "poetry2" );
			poetry2.setTitle( "Without you" );
			poetry2.setAuthor( "Paark" );
			poetry2.setContents( "Life is alone ~" );
			poetry2.setImageId( "Image2" );
			ret.add( poetry2 );
		}
		else if ( "poetry2".equals( startId ) )
		{
			final Poetry poetry3 = new Poetry();
			poetry3.setId( "poetry3" );
			poetry3.setTitle( "Big picture..." );
			poetry3.setAuthor( "Lee" );
			poetry3.setContents( "Open your ..." );
			poetry3.setImageId( "image3" );
			ret.add( poetry3 );
			
			
			final Poetry poetry4 = new Poetry();
			poetry4.setId( "poetry4" );
			poetry4.setTitle( "Rule the sky" );
			poetry4.setAuthor( "Sung" );
			poetry4.setContents( "Life is alone ~" );
			poetry4.setImageId( "image4" );
			ret.add( poetry4 );
		}
		
		return ret;
	}

}