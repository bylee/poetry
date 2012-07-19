package com.poetry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Poet
{

	@Id
	protected String username;
	
	@Column( name = "pen_name" )
	protected String penName;
	
	public Poet()
	{
	}
	
	public Poet( final String username, final String penName )
	{
		this.username = username;
		this.penName = penName;
	}

	public String getUsername()
	{
		return this.username;
	}

	/**
	 * @param name the user name to set
	 */
	public void setUsername( String name )
	{
		this.username = name;
	}

	/**
	 * @return the penName
	 */
	public String getPenName()
	{
		return penName;
	}

	/**
	 * @param penName the penName to set
	 */
	public void setPenName( String penName )
	{
		this.penName = penName;
	}

}
