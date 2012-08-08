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
	
	protected String titleFont;
	
	protected int titleSize;
	
	protected String titleColor;
	
	@ManyToOne
	@JoinColumn( name = "author" )
	protected Poet author;
	
	protected String contents;
	
	protected String contentsFont;
	
	protected int contentsSize;
	
	protected String contentsColor;
	
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
	
	
	/**
	 * @return the titleFont
	 */
	public String getTitleFont() {
		return titleFont;
	}

	/**
	 * @param titleFont the titleFont to set
	 */
	public void setTitleFont(String titleFont) {
		this.titleFont = titleFont;
	}

	/**
	 * @return the titleSize
	 */
	public int getTitleSize() {
		return titleSize;
	}

	/**
	 * @param titleSize the titleSize to set
	 */
	public void setTitleSize(int titleSize) {
		this.titleSize = titleSize;
	}

	/**
	 * @return the titleColor
	 */
	public String getTitleColor() {
		return titleColor;
	}

	/**
	 * @param titleColor the titleColor to set
	 */
	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	/**
	 * @return the contentsFont
	 */
	public String getContentsFont() {
		return contentsFont;
	}

	/**
	 * @param contentsFont the contentsFont to set
	 */
	public void setContentsFont(String contentsFont) {
		this.contentsFont = contentsFont;
	}

	/**
	 * @return the contentsSize
	 */
	public int getContentsSize() {
		return contentsSize;
	}

	/**
	 * @param contentsSize the contentsSize to set
	 */
	public void setContentsSize(int contentsSize) {
		this.contentsSize = contentsSize;
	}

	/**
	 * @return the contentsColor
	 */
	public String getContentsColor() {
		return contentsColor;
	}

	/**
	 * @param contentsColor the contentsColor to set
	 */
	public void setContentsColor(String contentsColor) {
		this.contentsColor = contentsColor;
	}

	@Override
	public
	String
	toString()
	{
		return "Poetry[" + getId() + "]";
	}
}
