package com.poetry;

import java.io.InputStream;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.poetry.dao.BinaryDao;
import com.poetry.dao.PoetryDao;
import com.poetry.dao.ReplyDao;
import com.poetry.dao.PoetDao;
import com.poetry.model.Binary;
import com.poetry.model.Following;
import com.poetry.model.Poet;
import com.poetry.model.Poetry;
import com.poetry.model.Reply;

import escode.util.StreamUtils;

public class
Install
{
	@Autowired
	DataSource dataSource;

	@Autowired
	protected SessionFactory sessionFactory;
	
	@Autowired
	protected PoetDao userDao;
	
	@Autowired
	protected BinaryDao binaryDao;
	
	@Autowired
	protected PoetryDao poetryDao;
	
	@Autowired
	protected ReplyDao replyDao;

	public static
	void
	main(
		final String[] args
	)
	throws Exception
	{
		final GenericXmlApplicationContext context =
			new GenericXmlApplicationContext( "classpath:com/poetry/install.xml" );
		
		Install instance = context.getBean( Install.class );
		instance.run();
	}
	
	public void run() throws SQLException, Exception
	{
		final Session session =
			SessionFactoryUtils.openSession( sessionFactory );
		session.setFlushMode( FlushMode.AUTO );
		TransactionSynchronizationManager.bindResource( sessionFactory, new SessionHolder( session ) );

		
		try
		{
			Poet bylee = new Poet( "bylee", "Bon-Yong Lee", "bylee", "ROLE_ADMIN,ROLE_USER" );
			Poet anjong = new Poet( "anjong", "Jong-Hyun Kwon", "anjong" );
			Poet csoonoosc = new Poet( "csoonoosc", "Choong-Soon Park", "csoonoosc" );
			Poet hellojintae = new Poet( "hellojintae", "Jin-Tae Jung", "hellojintae" );
			Poet hanseoung82 = new Poet( "hanseoung82", "Han-Seoung Sung", "hanseoung82" );
			userDao.addNewPoet( bylee );
			userDao.addNewPoet( anjong );
			userDao.addNewPoet( csoonoosc );
			userDao.addNewPoet( hellojintae );
			userDao.addNewPoet( hanseoung82 );
			
			final Binary binary = new Binary();
			binary.setName( "test.jpg" );
			binary.setOwner( bylee.getUsername() );
			binary.setMime( "image/jpg" );
			final InputStream in = getClass().getResourceAsStream( "/com/poetry/15585.jpg" );
			try
			{
				binary.setContents( StreamUtils.getBytes( in ) );
			}
			finally
			{
				StreamUtils.tryClose( in );
			}
			
			binaryDao.addBinary( binary );
			
			final Poetry poetry = new Poetry( "My First Poem", bylee, binary.getId() );
			poetry.setStar( 11 );
			
			poetryDao.addPoetry( poetry );
			
			replyDao.addReply( new Reply( poetry.getId(), anjong, "Good~~" ) );
			replyDao.addReply( new Reply( poetry.getId(), csoonoosc, "So so" ) );
			
			userDao.addFollowing( new Following( bylee.getUsername(), anjong.getUsername() ) );
			userDao.addFollowing( new Following( bylee.getUsername(), csoonoosc.getUsername() ) );
		}
		finally
		{
			TransactionSynchronizationManager.unbindResource( sessionFactory );
			session.flush();
			SessionFactoryUtils.closeSession( session );
		}
	}

}
