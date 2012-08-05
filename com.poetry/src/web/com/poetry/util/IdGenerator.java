package com.poetry.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;

import escode.KeyedFactory;
import escode.util.ObjectUtils;

public class
IdGenerator
implements KeyedFactory<Object, String>
{

	// GUID에 사용될 난수의 SEED생성기
	private static final SecureRandom SEEDER = new SecureRandom();

	// 16진수화한 IP
	private static String hexServerIP = null;

	/* (non-Javadoc)
	 * @see escode.KeyedFactory#create(java.lang.Object)
	 */
	public
	String
	create(
		final Object key
	)
	{
		final StringBuilder guid = new StringBuilder( 32 );

		// 시간값
		long timeNow = System.nanoTime();
		int timeLow = (int) timeNow& 0xFFFFFFFF;
		guid.append( ObjectUtils.hexFormat( timeLow, 8 ) );

		// 서버 IP
		if ( null == hexServerIP )
		{
			InetAddress localInetAddress = null;
			try
			{
				// get the inet address
				localInetAddress = InetAddress.getLocalHost();
			}
			catch( final UnknownHostException uhe )
			{
				try
				{
					localInetAddress = InetAddress.getByName( "localhost" );
				}
				catch ( final UnknownHostException e )
				{
					e.printStackTrace();
					return null;
				}
			}

			byte serverIP[] = localInetAddress.getAddress();

			hexServerIP = ObjectUtils.hexFormat( ObjectUtils.getInt( serverIP ), 8 );
		}
		guid.append( hexServerIP );

		// 객체 해쉬값
		guid.append( ObjectUtils.hexFormat( System.identityHashCode( key ), 8 ) );

		// 난수
		int node= -1;
		synchronized( SEEDER )
		{
			node = SEEDER.nextInt();
		}
		guid.append( ObjectUtils.hexFormat( node, 8 ) );
		return guid.toString();
	}

}
