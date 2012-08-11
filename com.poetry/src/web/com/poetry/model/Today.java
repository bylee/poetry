package com.poetry.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class
Today
implements Serializable, Comparable<Today>
{
	
	private static final long serialVersionUID = -8406626183534773276L;

	@Id
	protected Date date;
	
	@Id
	protected int target;
	
	protected String type;
	
	protected String poetry;
	
	public Today() {}
	
	public Today( final Date date, final int target )
	{
		this.date = date;
		this.target = target;
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
	 * @return the target
	 */
	public int getTarget()
	{
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget( int target )
	{
		this.target = target;
	}

	public String getType()
	{
		return this.type;
	}
	
	public void setType( final String type )
	{
		this.type = type;
	}
	
	/**
	 * @return the poet
	 */
	public String getPoetry()
	{
		return poetry;
	}

	/**
	 * @param poetry the poet to set
	 */
	public void setPoetry(
		final String poetry
	)
	{
		this.poetry = poetry;
	}

	public
	int
	compareTo(
		final Today other
	)
	{
		return this.target - other.target;
	}
	
	
	public String toString()
	{
		return date + ":" + target + "-" + target + "," + poetry;
	}

}
