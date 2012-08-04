package com.poetry.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MissionPoetry
{
	@Id
	protected String id;
	
	protected Date date = new Date();
	
	protected String poetryId;
	
	public MissionPoetry() {}
	
	public MissionPoetry( final String poetId )
	{
		this.poetryId = poetId;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( String id )
	{
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate( Date date )
	{
		this.date = date;
	}

	/**
	 * @return the poetryId
	 */
	public String getPoetryId()
	{
		return poetryId;
	}

	/**
	 * @param poetryId the poetryId to set
	 */
	public void setPoetryId( String poetryId )
	{
		this.poetryId = poetryId;
	}

	
}
