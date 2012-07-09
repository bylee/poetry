package com.poetry.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Poetry
{
	@Id
	protected String id;
	
	protected String title;
	
	protected String author;
	
	protected String contents;
	
	protected String imageId;

	protected Date createdDate;
	
	@Transient
	protected int good;
	
	@Transient
	protected int reply;

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
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle( String title )
	{
		this.title = title;
	}
	
	public String getAuthor()
	{
		return this.author;
	}
	
	public void setAuthor( final String author )
	{
		this.author = author;
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
	
	public String getImageId()
	{
		return this.imageId;
	}
	
	public void setImageId( final String imageId )
	{
		this.imageId = imageId;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate( Date createdDate )
	{
		this.createdDate = createdDate;
	}

	public int getGood()
	{
		return this.good;
	}
	
	public void setGood( final int good )
	{
		this.good = good;
	}
	
	public int getReply()
	{
		return this.reply;
	}
	
	public void setReply( final int reply )
	{
		this.reply = reply;
	}
	
}