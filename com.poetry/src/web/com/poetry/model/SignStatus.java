package com.poetry.model;

public class SignStatus
{
	protected String username;

	protected String status;

	public SignStatus()
	{
	}

	public SignStatus( final String username, String status )
	{
		this.username = username;
		this.status = status;
	}


	public String getUsername()
	{
		return this.username;
	}

	public void setUsername( final String username  )
	{
		this.username = username;
	}

	public String getStatus()
	{
		return this.status;
	}

	public void setStatus( final String status  )
	{
		this.status = status;
	}
}
