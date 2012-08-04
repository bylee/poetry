package com.poetry.service;

import static escode.util.StreamUtils.getBytes;

import java.io.FileInputStream;
import java.util.Date;

import com.poetry.model.Binary;
import com.poetry.model.Mission;
import com.poetry.model.MissionPoetry;

public class
BinaryServiceMock
implements BinaryService
{
	@Override
	public
	Binary
	getBinary(
		final String id
	)
	{
		if ( "image1".equals( id ) )
		{
			Binary bin = new Binary();
			bin.setName( "image1.jgp" );
			bin.setId( "image1" );
			bin.setMime( "image/jpeg" );
			try
			{
				bin.setContents( getBytes( new FileInputStream( "test/doc/image1.jpg" ), true ) );
			}
			catch ( Exception e )
			{
				throw new IllegalStateException( e );
			}
			return bin;
		}
		return null;
	}

	@Override
	public String upload( Binary binary )
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String upload(Mission mission) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Mission getMission() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Mission getMission(Date date) {
		throw new UnsupportedOperationException();
	}

	public void upload( MissionPoetry missionPoet )
	{
		// TODO Auto-generated method stub
		
	}

}
