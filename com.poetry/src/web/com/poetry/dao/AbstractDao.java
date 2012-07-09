package com.poetry.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import escode.KeyedFactory;

public class
AbstractDao
{
	
	protected static final int LIMIT = 20;

	protected final Logger logger = LoggerFactory.getLogger( getClass() );
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	@Autowired
	protected KeyedFactory<Object, String> idGenerator;
	
	public
	void
	setSessionFactory(
		final SessionFactory sessionFactory
	)
	{
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	public KeyedFactory<Object, String> getIdGenerator()
	{
		return idGenerator;
	}
	
	public
	void
	setIdGenerator(
		final KeyedFactory<Object, String> idGenerator
	)
	{
		this.idGenerator = idGenerator;
	}
	
	protected
	String
	generateId( Object obj )
	{
		return idGenerator.create( obj );
	}
	
	public void insert( Object obj )
	{
		logger.trace( "Insert {}", obj );
		getSession().persist( obj );
		getSession().save( obj );
	}
	
	public void update( Object obj )
	{
		getSession().update( obj );
		getSession().save( obj );
	}
	
	public void delete( Object obj )
	{
		getSession().delete( obj );
		getSession().save( obj );
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get( Class<T> clazz, Serializable id )
	{
		logger.trace( "Get {} from {}", id, clazz );
		getSession().flush();
		T ret = (T) getSession().get( clazz, id );
		logger.debug( "Result for {} :{}", id, ret );
		
		return ret;
	}
	
	public <T> boolean exists( Class<T> clazz, Serializable id )
	{
		T t = get( clazz, id );
		if ( null != t )
		{
			return false;
		}
		
		return true;
	}
	
	protected List<?> find(
		final String queryStr
	)
	{
		getSession().flush();
		final Query query = getSession().createQuery( queryStr );
		
		return query.list();

	}

}
