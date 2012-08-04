package com.poetry.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reply
{
	@Id
	protected String id;

	@Column( nullable = false )
	protected String targetId;
	
	@ManyToOne
	@JoinColumn( name = "writer" )
	protected Poet writer;
	
	protected String contents;
	
	protected Date createdDate;
	
	public Reply() {}
	
	public Reply( final String id )
	{
		this.id = id;
	}
	
	public Reply( final String targetId, final Poet writer, final String contents )
	{
		this.targetId = targetId;
		this.writer= writer;
		this.contents = contents;
	}
	
	public Reply(
		final String id,
		final String targetId,
		final Poet writer,
		final String contens
	)
	{
		this.id = id;
		this.targetId = targetId;
		this.writer = writer;
		this.contents = contens;
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
	 * @return the user
	 */
	public Poet getWriter()
	{
		return writer;
	}

	/**
	 * @param writer the user to set
	 */
	public void setWriter( Poet writer )
	{
		this.writer = writer;
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
		return createdDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate( Date createDate )
	{
		this.createdDate = createDate;
	}

	
}
