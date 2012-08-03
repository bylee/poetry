package com.poetry.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
		if ( !( obj instanceof Bookmark ) )
		{
			return false;
		}
		Bookmark other = (Bookmark) obj;
		return new EqualsBuilder()
		.append( poetryId, other.poetryId )
		.append( poetId, other.poetId )
		.isEquals();
	}
	

}
