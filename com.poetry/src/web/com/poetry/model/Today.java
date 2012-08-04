package com.poetry.model;

import java.sql.Date;

public class Today
{
	protected Date date;
	
	protected String target;
	
	protected String poet;

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
	 * @return the target
	 */
	public String getTarget()
	{
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget( String target )
	{
		this.target = target;
	}

	/**
	 * @return the poet
	 */
	public String getPoet()
	{
		return poet;
	}

	/**
	 * @param poet the poet to set
	 */
	public void setPoet( String poet )
	{
		this.poet = poet;
	}
	
	

}
