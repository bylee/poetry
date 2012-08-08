package com.poetry.install;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import escode.util.StreamUtils;

public
class
AbstractInstall
{
	protected final Logger logger = LoggerFactory.getLogger( getClass() );
	
	protected
	byte[] load(
		final String name
	)
	throws IOException
	{
		logger.trace( "Trying load image :{}", name );
		final Class<?> clazz = getClass();
		final Package pack = clazz.getPackage();
		final String packageName = pack.getName();
		final String path = "/" + packageName.replace( '.', '/' ) +"/" + name;
		final InputStream in = getClass().getResourceAsStream( path );
		return StreamUtils.getBytes( in, true );
	}
	
	protected
	String
	read(
		final String name
	)
	throws IOException
	{
		logger.trace( "Trying read file :{}", name );
		final Class<?> clazz = getClass();
		final Package pack = clazz.getPackage();
		final String packageName = pack.getName();
		final String path = "/" + packageName.replace( '.', '/' ) +"/" + name + ".txt";
		final InputStream in = getClass().getResourceAsStream( path );
		if ( null == in )
		{
			logger.error( "File path :{}", path );
		}
		final Reader reader = new InputStreamReader( in );
		return StreamUtils.getString( reader, true );
	}
}
