package com.poetry.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
public class
Block
implements Serializable
{

	private static final long serialVersionUID = -2698184432992671157L;

	@Id
	protected String following;
	
	@Id
	protected String follower;

	public Block() {}
	
	public Block( final String following, final String follower )
	{
		this.following = following;
		this.follower = follower;
	}
	
	/**
	 * @return the following
	 */
	public
	String
	getFollowing()
	{
		return following;
	}

	/**
	 * @param following the following to set
	 */
	public
	void
	setFollowing(
		final String following
	)
	{
		this.following = following;
	}

	/**
	 * @return the follower
	 */
	public
	String
	getFollower()
	{
		return follower;
	}

	/**
	 * @param follower the follower to set
	 */
	public
	void
	setFollower(
		final String follower
	)
	{
		this.follower = follower;
	}

	@Override
	public int hashCode()
	{
		return new HashCodeBuilder()
		.append( following )
		.append( follower )
		.toHashCode();
	}
	
	@Override
	public boolean equals(
		final Object obj
	)
	{
		if ( !( obj instanceof Block ) )
		{
			return false;
		}
		Block other = (Block) obj;
		return new EqualsBuilder()
		.append( following, other.following )
		.append( follower, other.follower )
		.isEquals();
	}
	
	public String toString()
	{
		return following + ":" + follower;
	}
	
	


}
