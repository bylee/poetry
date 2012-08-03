package com.poetry.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


@Entity
public class
Star
implements Serializable
{
	
	private static final long serialVersionUID = 1068296127875124440L;

	@Id
	protected String poetryId;
	
	@Id
	protected String poetId;
	
	public Star() {}
	
	public Star( final String poetryId, final String poetId )
	{
		this.poetryId = poetryId;
		this.poetId = poetId;
	}

	/**
	 * @return the poetryId
	 */
	public
	String
	getPoetryId()
	{
		return poetryId;
	}

	/**
	 * @param poetryId the poetryId to set
	 */
	public
	void
	setPoetryId(
		final String poetryId
	)
	{
		this.poetryId = poetryId;
	}

	/**
	 * @return the poetId
	 */
	public
	String
	getPoetId()
	{
		return poetId;
	}

	/**
	 * @param poetId the poetId to set
	 */
	public
	void
	setPoetId(
		final String poetId
	)
	{
		this.poetId = poetId;
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder()
		.append( poetryId )
		.append( poetId )
		.toHashCode();
	}
	
	@Override
	public boolean equals(
		final Object obj
	)
	{
		if ( !( obj instanceof Star ) )
		{
			return false;
		}
		Star other = (Star) obj;
		return new EqualsBuilder()
		.append( poetryId, other.poetryId )
		.append( poetId, other.poetId )
		.isEquals();
	}

}
