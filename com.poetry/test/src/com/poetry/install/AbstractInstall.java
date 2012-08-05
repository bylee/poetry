package com.poetry.install;

import java.io.IOException;
import java.io.InputStream;

import escode.util.StreamUtils;

public
class
AbstractInstall
{
	protected
	byte[] load(
		final String name
	)
	throws IOException
	{
		final Class<?> clazz = getClass();
		final Package pack = clazz.getPackage();
		final String packageName = pack.getName();
		final InputStream in = getClass().getResourceAsStream( "/" + packageName.replace( '.', '/' ) +"/" + name );
		try
		{
			if ( null == in )
			{
				throw new NullPointerException();
			}
			return StreamUtils.getBytes( in );
		}
		finally
		{
			StreamUtils.tryClose( in );
		}
	}
}
