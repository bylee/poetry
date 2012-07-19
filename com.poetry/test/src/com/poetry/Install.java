package com.poetry;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

public class
Install
implements Runnable
{
	public static void main( String[] args )
	{
		Install instance = new Install();
		instance.run();
	}
	
	public void run()
	{
		final GenericXmlApplicationContext context =
			new GenericXmlApplicationContext( "classpath:com/poetry/install.xml" );
		

		final SessionFactory sessionFactory =
			context.getBean( SessionFactory.class );
		
		final Session session =
			SessionFactoryUtils.openSession( sessionFactory );
		session.setFlushMode( FlushMode.AUTO );

		try
		{
			
			
		}
		finally
		{
			session.flush();
			SessionFactoryUtils.closeSession( session );
		}
		
		
	}

}
