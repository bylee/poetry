package com.poetry.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Reply
{
	protected String id;
	
	protected String targetId;
	
	protected String targetType;
	
	protected String user;
	
	protected String contents;
	
	protected Date createDate;
	
	public Reply()
	{
	}
	
	public Reply( final String id )
	{
		this.id = id;
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
	 * @return the targetId
	 */
	public String getTargetId()
	{
		return targetId;
	}

	/**
	 * @param targetId the targetId to set
	 */
	public void setTargetId( String targetId )
	{
		this.targetId = targetId;
	}

	/**
	 * @return the targetType
	 */
	public String getTargetType()
	{
		return targetType;
	}

	/**
	 * @param targetType the targetType to set
	 */
	public void setTargetType( String targetType )
	{
		this.targetType = targetType;
	}

	/**
	 * @return the user
	 */
	public String getUser()
	{
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser( String user )
	{
		this.user = user;
	}

	/**
	 * @return the contents
	 */
	public String getContents()
	{
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents( String contents )
	{
		this.contents = contents;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate()
	{
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate( Date createDate )
	{
		this.createDate = createDate;
	}

	
}
