package com.poetry.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mission
{
	@Id
	protected String id;
	
	protected Date date;
	
	protected String imageId;
	
	protected String description;
	
	public
	Mission()
	{}
	
	public
	Mission(
		final Date date,
		final String imageId,
		final String description
	)
	{
		this.date = date;
		this.imageId = imageId;
		this.description = description;
	}
	
	public
	String
	getId()
	{
		return this.id;
	}
	
	public
	void
	setId(
		final String id
	)
	{
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public
	void
	setImageId(
		final String imageId
	)
	{
		this.imageId = imageId;
	}
	
	public String getImageId()
	{
		return this.imageId;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	

	@Override
	public String toString() {
		return date + ":" + imageId+"-" + description;
	}
	
	
	

}
