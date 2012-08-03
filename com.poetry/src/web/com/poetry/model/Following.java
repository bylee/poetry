package com.poetry.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
public class
Following
implements Serializable
{
	private static final long serialVersionUID = -8644519565492237445L;

	@Id
	protected String following;
	
	@Id
	protected String follower;
	
	public Following() {}
	
	public Following(
		final String following,
		final String follower
	)
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
		if ( !( obj instanceof Following ) )
		{
			return false;
		}
		Following other = (Following) obj;
		return new EqualsBuilder()
		.append( following, other.following )
		.append( follower, other.follower )
		.isEquals();
	}

	
}
