package com.poetry.install;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.poetry.AbstractTestCase;
import com.poetry.dao.FollowingDao;
import com.poetry.dao.MissionDao;
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

@Component
public class
InstallAll
extends AbstractTestCase
{
	@Autowired
	protected SessionFactory sessionFactory;
	
	@Autowired
	InstallUser user;

	@Autowired
	InstallBinary binary;
	
	@Autowired
	protected PoetryDao poetryDao;
	
	@Autowired
	protected ReplyDao replyDao;
	
	@Autowired
	protected StarDao starDao;
	
	protected Poet bylee, anjong, csoonoosc, hellojintae, hanseoung82;
	
	protected Binary image1, image2_1, image2_2, image3_1, image3_2, missionImage;


	public static
	void
	main(
		final String[] args
	)
	throws Exception
	{
		final GenericXmlApplicationContext context =
			new GenericXmlApplicationContext( "classpath:com/poetry/install/install.xml" );
		
		InstallAll instance = context.getBean( InstallAll.class );
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
			
			final Map<String, Poet> users = user.install();
			bylee = users.get( "bylee" );
			anjong = users.get( "anjong" );
			csoonoosc = users.get( "csoonoosc" );
			hellojintae = users.get( "hellojintae" );
			hanseoung82 = users.get( "hanseoung82" );
			
			final Map<String, Binary> binaries = binary.install();
			image1 = binaries.get( "mainimage.jpeg" );
			image2_1 = binaries.get( "image2-1.jpeg" );
			image2_2 = binaries.get( "image2-2.jpeg" );
			image3_1 = binaries.get( "image3-1.jpeg" );
			image3_2 = binaries.get( "image3-2.jpeg" );
			missionImage = binaries.get( "fallroad.jpeg" );
			
			final Poetry poetry1 = new Poetry( "My First Poem", bylee, "contents1", image1.getId() );
			poetryDao.addPoetry( poetry1 );
			
			replyDao.addReply( new Reply( poetry1.getId(), anjong, "Good~~" ) );
			replyDao.addReply( new Reply( poetry1.getId(), csoonoosc, "So so" ) );
			
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

			installFollowing( bylee, hanseoung82 );
			installFollowing( bylee, anjong );
			installFollowing( anjong, bylee );
			installFollowing( anjong, csoonoosc );
			installFollowing( csoonoosc, hanseoung82 );
			installFollowing( hanseoung82, hellojintae );
			installFollowing( hellojintae, bylee );
			
			final Mission mission = new Mission( new Date(), missionImage.getId(), "이 사진은 강원도 지역의 시골길을 찍은 것이며, 멀리 보이는 길을 가르쳐주는 가을 나무들이 있는 풍경입니다. 날짜는 2002년 10월경입니다." );
			missionDao.addNewMission( mission );

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
	
	@Autowired
	protected FollowingDao followingDao;
	
	
	protected
	void
	installFollowing( Poet following, Poet follower )
	{
		followingDao.addFollowing( new Following( following.getUsername(), follower.getUsername() ) );
	}
	

}
