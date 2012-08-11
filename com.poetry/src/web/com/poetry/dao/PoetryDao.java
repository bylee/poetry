package com.poetry.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

import com.poetry.model.Poetry;
import com.poetry.model.Today;


@Repository
public class
PoetryDao
extends AbstractDao
{
	public
	void
	addNewPoetry(
		final Poetry poetry
	)
	{
		poetry.setId( generateId( poetry ) );
		super.insert( poetry );
	}
	
	@SuppressWarnings("unchecked")
	public
	Map<Today, Poetry>
	getUserSpecificTodayPoetry(
		final Date date
	)
	{
		final TreeMap<Today, Poetry> ret = new TreeMap<Today, Poetry>();
		
		final List<Object[]> results = (List<Object[]>) find(
			"from Poetry p, Today t " +
			"where t.poetry = p.id and t.date = ?",
			date
		);
		
		for ( final Object[] result : results )
		{
			final Poetry poetry = (Poetry) result[0];
			final Today today = (Today) result[1];
			logger.trace( "Poetry :{} Today :{}", poetry, today );
			
			ret.put( today, poetry );
		}
		
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public
	List<String>
	getMostStaredTodayPoetries(
		final Date date
	)
	{
		final Collection<Object[]> results = (Collection<Object[]>) find(
			"select poetry.id, count( poetry.id ) as rank " +
			"from Poetry poetry, Star star, MissionPoetry mp " +
			"where mp.id = poetry.id and poetry.id = star.poetryId and poetry.createdDate = ? " +
			"group by poetry.id " +
			"order by rank desc",
			date
		);
		
		long max = 0;
		
		final ArrayList<String> ret = new ArrayList<String>();
		
		for ( final Object[] result : results )
		{
			long nStar = (Long) result[1];
			if ( max <= nStar )
			{
				max = nStar;
				ret.add( (String) result[0] );
			}
			else
			{
				return ret;
			}
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public
	List<Poetry>
	listPoetry()
	{
		final String query =
			"from Poetry poetry";
		
		return (List<Poetry>) find( query ); 
	}

	public
	Poetry
	getPoetry(
		final String id
	)
	{
		final Poetry poetry = get( Poetry.class, id );
		
		if ( null == poetry )
		{
			return null;
		}
		
		return poetry;
	}
	
	public
	int
	getTheNumberOfPoetries(
		final String username
	)
	{
		return ((Long) findOne(
			"select count(poetry.author) " +
			"from Poetry poetry " +
			"where poetry.author = ?",
			username
		) ).intValue();
	}

	@SuppressWarnings("unchecked")
	public
	List<Poetry>
	getPoetryOf(
		final String poetId
	)
	{
		return (List<Poetry>) find(
			"from Poetry poetry " +
			"where poetry.author =  ? " +
			"order by poetry.id desc",
			poetId
		);
	}

	@SuppressWarnings("unchecked")
	public
	List<Poetry>
	getPoetryOf(
		final String poetId,
		final String start
	)
	{
		return (List<Poetry>) find(
			"from Poetry poetry " +
			"where poetry.author =  ? and poetry.id < ? " +
			"order by poetry.id desc",
			poetId, start
		);
	}
	
	@SuppressWarnings("unchecked")
	public
	List<Poetry>
	getNewsfeed(
		final String username
	)
	{
		final List<Object[]> result =
			(List<Object[]>) find(
				"from Poetry poetry, Following following " +
				"where poetry.author = following.following " +
				"and following.follower = ? " +
				"order by poetry.id",
				username
			);
		
		return extract( result, 0 );
	}

	@SuppressWarnings("unchecked")
	public
	List<Poetry>
	getNewsfeed(
		final String username,
		final String start
	)
	{
		final List<Object[]> result = (List<Object[]>) find(
			"from Poetry poetry, Following following " +
			"where poetry.author = following.following " +
			"and following.follower = ? " +
			"and poetry.id > ? " +
			"order by poetry.id",
			username, start
		);
		
		return extract( result, 0 );
	}

}
