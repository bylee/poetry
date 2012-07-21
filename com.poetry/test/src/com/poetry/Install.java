package com.poetry;

import java.io.IOException;
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
			
			final Binary image1 = new Binary();
			image1.setName( "mainimage.jpeg" );
			image1.setOwner( bylee.getUsername() );
			image1.setMime( "image/jpg" );
			image1.setContents( load( image1.getName() ) );
			binaryDao.addBinary( image1 );
			
			final Binary image2_1 = new Binary();
			image2_1.setName( "image2-1.jpeg" );
			image2_1.setOwner( bylee.getUsername() );
			image2_1.setMime( "image/jpg" );
			image2_1.setContents( load( image2_1.getName() ) );
			binaryDao.addBinary( image2_1 );
			
			final Binary image2_2 = new Binary();
			image2_2.setName( "image2-2.jpeg" );
			image2_2.setOwner( bylee.getUsername() );
			image2_2.setMime( "image/jpg" );
			image2_2.setContents( load( image2_2.getName() ) );
			binaryDao.addBinary( image2_2 );
			
			final Binary image3_1 = new Binary();
			image3_1.setName( "image3-1.jpeg" );
			image3_1.setOwner( bylee.getUsername() );
			image3_1.setMime( "image/jpg" );
			image3_1.setContents( load( image3_1.getName() ) );
			binaryDao.addBinary( image3_1 );
			
			final Binary image3_2 = new Binary();
			image3_2.setName( "image3-2.jpeg" );
			image3_2.setOwner( bylee.getUsername() );
			image3_2.setMime( "image/jpg" );
			image3_2.setContents( load( image3_2.getName() ) );
			binaryDao.addBinary( image3_2 );
			
			final Poetry poetry1 = new Poetry( "My First Poem", bylee, image1.getId() );
			poetry1.setStar( 11 );
			poetryDao.addPoetry( poetry1 );
			
			replyDao.addReply( new Reply( poetry1.getId(), anjong, "Good~~" ) );
			replyDao.addReply( new Reply( poetry1.getId(), csoonoosc, "So so" ) );
			
			userDao.addFollowing( new Following( bylee.getUsername(), anjong.getUsername() ) );
			userDao.addFollowing( new Following( bylee.getUsername(), csoonoosc.getUsername() ) );
			
			final Poetry poetry2 = new Poetry( "Second", bylee, image2_1.getId() );
			poetry2.setStar( 4 );
			poetryDao.addPoetry( poetry2 );
			
			replyDao.addReply( new Reply( poetry2.getId(), anjong, "Good~~" ) );
			replyDao.addReply( new Reply( poetry2.getId(), csoonoosc, "So so" ) );

			final Poetry poetry3 = new Poetry( "3", bylee, image2_2.getId() );
			poetry3.setStar( 4 );
			poetryDao.addPoetry( poetry3 );
			
			replyDao.addReply( new Reply( poetry3.getId(), csoonoosc, "So so" ) );

			final Poetry poetry4 = new Poetry( "4", csoonoosc, image3_1.getId() );
			poetry4.setStar( 4 );
			poetryDao.addPoetry( poetry4 );
			
			replyDao.addReply( new Reply( poetry4.getId(), anjong, "Good~~" ) );
			replyDao.addReply( new Reply( poetry4.getId(), hellojintae, "So so" ) );

			final Poetry poetry5 = new Poetry( "Last", anjong, image3_2.getId() );
			poetry5.setStar( 4 );
			poetryDao.addPoetry( poetry5 );
			
			replyDao.addReply( new Reply( poetry5.getId(), bylee, "Good~~" ) );
			replyDao.addReply( new Reply( poetry5.getId(), csoonoosc, "So so" ) );

		}
		finally
		{
			TransactionSynchronizationManager.unbindResource( sessionFactory );
			session.flush();
			SessionFactoryUtils.closeSession( session );
		}
	}
	
	public
	byte[] load(
		final String name
	)
	throws IOException
	{
		final InputStream in = getClass().getResourceAsStream( "/com/poetry/" + name );
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
