package com.poetry.install;

import java.sql.SQLException;
import java.util.Map;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.poetry.model.Mission;
import com.poetry.model.Poet;
import com.poetry.model.Poetry;

@Component
public class
InstallAll
{
	protected Logger logger = LoggerFactory.getLogger( getClass() );
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	@Autowired
	protected InstallPoet user;
	
	@Autowired
	protected InstallMission mission;
	
	@Autowired
	protected InstallFollowing following;

	@Autowired
	protected InstallPoetry poetry;
	
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
			// 사용자를 등록한다.
			final Map<String, Poet> users = user.install();
			logger.info( "Users :{}", users );
			
			// 오늘의 미션을 등록한다.
			final Map<String, Mission> missions = mission.install();
			logger.info( "Missions :{}", missions );
			
			// 사용자간의 관계를 등록한다.
			final Map<String, Object> relations = following.install();
			logger.info( "Followings :{}", relations );
			
			// 시를 등록한다.
			poetry.setPoets( users );
			poetry.setMissions( missions );
			final Map<String, Poetry> poeties = poetry.install();
			logger.info( "Poetries :{}", poeties );
			
		}
		finally
		{
			TransactionSynchronizationManager.unbindResource( sessionFactory );
			session.flush();
			SessionFactoryUtils.closeSession( session );
		}
	}
	
}
