package com.poetry.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DailySubject
{
	@Id
	protected String id;
	
	protected Date date;
	
	protected String imageId;

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
	 * @return the imageId
	 */
	public String getImageId()
	{
		return imageId;
	}

	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId( String imageId )
	{
		this.imageId = imageId;
	}

	
}
