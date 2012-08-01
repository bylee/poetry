package com.poetry.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class
Bookmark
implements Serializable
{
	private static final long serialVersionUID = -5817631451494391476L;

	@Id
	protected String poetryId;
	
	@Id
	protected String poetId;
	
	public
	Bookmark() {}
	
	public
	Bookmark(
		final String poetryId,
		final String poetId
	)
	{
		this.poetryId = poetryId;
		this.poetId = poetId;
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

	/**
	 * @return the poetId
	 */
	public String getPoetId()
	{
		return poetId;
	}

	/**
	 * @param poetId the poetId to set
	 */
	public void setPoetId( String poetId )
	{
		this.poetId = poetId;
	}
	
	

}
