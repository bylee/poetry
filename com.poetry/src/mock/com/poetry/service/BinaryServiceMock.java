package com.poetry.service;

import static escode.util.StreamUtils.getBytes;

import java.io.FileInputStream;

import com.poetry.model.Binary;

public class
BinaryServiceMock
extends BinaryService
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
	

}
