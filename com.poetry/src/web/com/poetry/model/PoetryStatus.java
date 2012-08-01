package com.poetry.model;

public class
PoetryStatus
{
	protected String poetId;
	
	protected String poetryId;
	
	protected int theNumberOfStar;
	
	protected int theNumberOfReply;
	
	protected boolean bStar;
	
	protected boolean bBookmark;
	
	protected boolean bFollowing;
	
	public PoetryStatus() {}
	
	public PoetryStatus(
		final String poetryId,
		final String poetId
	)
	{
		this.poetryId = poetryId;
		this.poetId = poetId;
	}
	
	public String getPoetryId()
	{
		return this.poetryId;
	}
	
	public String getPoetId()
	{
		return this.poetId;
	}
	
	
	public int getStar()
	{
		return this.theNumberOfStar;
	}
	
	public void setStar( final int star )
	{
		this.theNumberOfStar = star;
	}
	
	public int getReply()
	{
		return this.theNumberOfReply;
	}
	
	public void setReply( final int reply )
	{
		this.theNumberOfReply = reply;
	}
	

	public boolean isStar()
	{
		return this.bStar;
	}
	
	public void setStar( final boolean bStar )
	{
		this.bStar = bStar;
	}
	
	public boolean isBookmark()
	{
		return this.bBookmark;
	}
	
	public void setBookmark( final boolean bBookmark )
	{
		this.bBookmark = bBookmark;
	}
	
	public boolean isFollowing()
	{
		return this.bFollowing;
	}
	
	public void setFollowing( final boolean bFollowing )
	{
		this.bFollowing = bFollowing;
	}
}
