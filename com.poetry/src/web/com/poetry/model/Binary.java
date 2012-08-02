package com.poetry.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table( name = "file" )
public class
Binary
{
	@Id
	protected String id;
	
	protected String name;
	
	protected String owner;
	
	protected String mime;
	
	@JsonIgnore
	protected byte[] contents;
	
	@Transient
	@JsonIgnore
	protected CommonsMultipartFile uploadFile;
	
	@Transient
	protected String tag;

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
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName( String name )
	{
		this.name = name;
	}
	
	public String getOwner()
	{
		return this.owner;
	}
	
	public
	void
	setOwner(
		final String owner
	)
	{
		this.owner = owner;
	}

	/**
	 * @return the mime
	 */
	public String getMime()
	{
		return mime;
	}

	/**
	 * @param mime the mime to set
	 */
	public void setMime( String mime )
	{
		this.mime = mime;
	}

	/**
	 * @return the contents
	 */
	public byte[] getContents()
	{
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents( byte[] contents )
	{
		this.contents = contents;
	}
	
	public CommonsMultipartFile getUploadFile()
	{
		return this.uploadFile;
	}
	
	public
	void
	setUploadFile(
		final CommonsMultipartFile uploadFile
	)
	{
		this.uploadFile = uploadFile;
	}
	
	public
	String
	getTag()
	{
		return this.tag;
	}
	
	public void
	setTag(
		final String tag
	)
	{
		this.tag = tag;
	}
	

	public int hashCode()
	{
		return new HashCodeBuilder().append( id ).toHashCode();
	}
	
	public boolean equals( Object obj )
	{
		if ( !( obj instanceof Binary ) )
		{
			return false;
		}
		final Binary bin = (Binary) obj;
		return new EqualsBuilder().append( id, bin.id ).isEquals();
	}
	
}
