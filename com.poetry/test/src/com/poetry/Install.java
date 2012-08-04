package com.poetry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

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
import com.poetry.dao.MissionDao;
import com.poetry.dao.PoetDao;
import com.poetry.dao.PoetryDao;
import com.poetry.dao.ReplyDao;
import com.poetry.dao.StarDao;
import com.poetry.model.Binary;
import com.poetry.model.Following;
import com.poetry.model.Mission;
import com.poetry.model.Poet;
import com.poetry.model.Poetry;
import com.poetry.model.Reply;
import com.poetry.model.Star;

public class
Install
extends AbstractTestCase
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
	
	@Autowired
	protected StarDao starDao;

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
			
			final Poetry poetry1 = new Poetry( "My First Poem", bylee, "contents1", image1.getId() );
			poetryDao.addPoetry( poetry1 );
			
			replyDao.addReply( new Reply( poetry1.getId(), anjong, "Good~~" ) );
			replyDao.addReply( new Reply( poetry1.getId(), csoonoosc, "So so" ) );
			
			userDao.addFollowing( new Following( bylee.getUsername(), anjong.getUsername() ) );
			userDao.addFollowing( new Following( bylee.getUsername(), csoonoosc.getUsername() ) );
			
			final Poetry poetry2 = new Poetry( "Second", anjong, "contents2", image2_1.getId() );
			poetryDao.addPoetry( poetry2 );
			
			replyDao.addReply( new Reply( poetry2.getId(), bylee, "Good~~" ) );
			replyDao.addReply( new Reply( poetry2.getId(), csoonoosc, "So so" ) );

			final Poetry poetry3 = new Poetry( "3", csoonoosc, "contents3", image2_2.getId() );
			poetryDao.addPoetry( poetry3 );
			
			replyDao.addReply( new Reply( poetry3.getId(), csoonoosc, "So so" ) );

			final Poetry poetry4 = new Poetry( "4", hellojintae, "contents4", image3_1.getId() );
			poetryDao.addPoetry( poetry4 );
			
			replyDao.addReply( new Reply( poetry4.getId(), anjong, "Good~~" ) );
			replyDao.addReply( new Reply( poetry4.getId(), hellojintae, "So so" ) );

			final Poetry poetry5 = new Poetry( "Last", hanseoung82, "contents5", image3_2.getId() );
			poetryDao.addPoetry( poetry5 );
			
			replyDao.addReply( new Reply( poetry5.getId(), bylee, "Good~~" ) );
			replyDao.addReply( new Reply( poetry5.getId(), csoonoosc, "So so" ) );
			
			starDao.addStar( new Star( poetry1.getId(), anjong.getUsername() ) );
			starDao.addStar( new Star( poetry1.getId(), hanseoung82.getUsername() ) );
			
			starDao.addStar( new Star( poetry2.getId(), hanseoung82.getUsername() ) );
			starDao.addStar( new Star( poetry2.getId(), bylee.getUsername() ) );
			starDao.addStar( new Star( poetry2.getId(), hellojintae.getUsername() ) );

		}
		finally
		{
			TransactionSynchronizationManager.unbindResource( sessionFactory );
			session.flush();
			SessionFactoryUtils.closeSession( session );
		}
	}

	@Autowired
	protected MissionDao missionDao;
	
	protected
	void
	installMission()
	throws IOException
	{
		final Binary binary = new Binary();
		binary.setName( "fallroad.jpeg" );
		binary.setOwner( "bylee" );
		binary.setMime( "image/jpg" );
		binary.setContents( load( binary.getName() ) );
		binaryDao.addBinary( binary );
		final Mission mission = new Mission( new Date(), binary.getId(), "이 사진은 강원도 지역의 시골길을 찍은 것이며, 멀리 보이는 길을 가르쳐주는 가을 나무들이 있는 풍경입니다. 날짜는 2002년 10월경입니다." );
		missionDao.addNewMission( mission );
	}
	

}
