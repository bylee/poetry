package com.poetry.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class
Poetry
{
	@Id
	protected String id;
	
	protected String title;
	
	@ManyToOne
	@JoinColumn( name = "author" )
	protected Poet author;
	
	protected String contents;
	
	protected String image;
	
	protected Date createdDate = new Date();
	
	@Transient
	protected int stars;
	
	@Transient
	protected int replys;
	
	@Transient
	protected boolean bStar;
	
	@Transient
	protected boolean bBookmark;
	
	@Transient
	protected boolean bFollowing;
	
	public Poetry()
	{
	}
	
	public Poetry(
		final String title,
		final Poet author,
		final String contents,
		final String image
	)
	{
		this.title = title;
		this.author = author;
		this.contents = contents;
		this.image = image;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( String id )
	{
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle( String title )
	{
		this.title = title;
	}
	
	public Poet getAuthor()
	{
		return this.author;
	}
	
	public void setAuthor( final Poet author )
	{
		this.author = author;
	}

	/**
	 * @return the contents
	 */
	public String getContents()
	{
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents( String contents )
	{
		this.contents = contents;
	}
	
	public String getImage()
	{
		return this.image;
	}
	
	public void setImage( final String image )
	{
		this.image = image;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate( Date createdDate )
	{
		this.createdDate = createdDate;
	}

	public int getStars()
	{
		return this.stars;
	}
	
	public void setStars( final int star )
	{
		this.stars = star;
	}
	
	public int getReplys()
	{
		return this.replys;
	}
	
	public void setReplys( final int reply )
	{
		this.replys = reply;
	}
	
	public boolean isStar()
	{
		return bStar;
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
	
	public void setFollowing(
		final boolean bFollowing
	)
	{
		this.bFollowing = bFollowing;
	}

	@Override
	public
	String
	toString()
	{
		return "Poetry[" + getId() + "]";
	}
}
