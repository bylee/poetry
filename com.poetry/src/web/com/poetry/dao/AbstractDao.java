package com.poetry.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
	
	protected
	Session
	getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	public
	KeyedFactory<Object, String>
	getIdGenerator()
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
	generateId(
		final Object obj
	)
	{
		return idGenerator.create( obj );
	}
	
	public
	void
	insert(
		final Object obj
	)
	{
		logger.trace( "Insert {}", obj );
		getSession().persist( obj );
		getSession().save( obj );
	}
	
	public
	void
	update(
		final Object obj
	)
	{
		getSession().update( obj );
		getSession().save( obj );
	}
	
	public
	void
	delete(
		final Object obj
	)
	{
		getSession().delete( obj );
		getSession().save( obj );
	}
	
	public
	int
	delete(
		final String queryStr,
		final Object... args
	)
	{
		Session session = getSession();
		session.flush();
		final Query query = session.createQuery( queryStr );
		bind( query, args );
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public <T>
	T
	get(
		final Class<T> clazz,
		final Serializable id
	)
	{
		logger.trace( "Get {} from {}", id, clazz );
		getSession().flush();
		T ret = (T) getSession().get( clazz, id );
		logger.debug( "Result for {} :{}", id, ret );
		return ret;
	}
	
	public <T>
	boolean
	exists(
		final Class<T> clazz,
		final Serializable id
	)
	{
		final T t = get( clazz, id );
		if ( null != t )
		{
			return false;
		}
		
		return true;
	}
	
	protected
	List<?>
	find(
		final String queryStr,
		final Object... args
	)
	{
		Session session = getSession();
		session.flush();
		final Query query = session.createQuery( queryStr );
		bind( query, args );
		query.setMaxResults( LIMIT );
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	protected <T>
	T
	findOne(
		final String queryStr,
		final Object... args
	)
	{
		getSession().flush();
		final Query query = getSession().createQuery( queryStr );
		bind( query, args );
		return (T) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	protected static <T>
	List<T>
	extract(
		final List<Object[]> inputs,
		final int index
	)
	{
		final List<T> ret = new ArrayList<T>();
		
		for ( final Object[] input : inputs )
		{
			ret.add( (T) input[index] );
		}
		
		return ret;
	}

	
	
	protected
	void
	bind(
		final Query query,
		final Object... args
	)
	{
		for ( int i = 0, n = args.length ; i<n ; ++i )
		{
			final Object arg = args[i];
			if ( arg instanceof String )
			{
				query.setString( i, (String) args[i] );
			}
			else if ( arg instanceof Date )
			{
				query.setDate( i, (Date) args[i] );
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}

	}

}
