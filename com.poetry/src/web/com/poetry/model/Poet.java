package com.poetry.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import escode.util.CollectionUtils;

@Entity
public class
Poet
implements UserDetails
{
	private static final long serialVersionUID = 7403546217867181618L;
	
	protected static String DEFAULT_AUTHORITY = "ROLE_USER";
	
	@Id
	protected String username;
	
	@Column( name = "pen_name" )
	protected String penName;
	
	protected String email;
	
	@JsonIgnore
	protected String password;
	
	@JsonIgnore
	protected String authority = DEFAULT_AUTHORITY;
	
	@Transient
	protected int nPoetries;
	
	@Transient
	protected int nClips;
	
	@Transient
	protected int nFollowings;
	
	@Transient
	protected int nFollowers;
	
	
	public Poet()
	{}
	
	public Poet( final String username, final String penName )
	{
		this( username, penName, null );
	}
	public Poet( final String username, final String penName, final String password )
	{
		this( username, penName, password, null );
	}
	
	public Poet(
		final String username,
		final String penName,
		final String password,
		final String authority
	)
	{
		this.username = username;
		this.penName = penName;
		this.password = password;
		if ( null == authority )
		{
			return ;
		}
		setAuthority( authority );
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

	/**
	 * @return
	 */
	public String getPassword()
	{
		return this.password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword( String password )
	{
		this.password = password;
	}
	
	public
	void setAuthority(
		final String authority
	)
	{
		this.authority = authority;
	}
	
	public
	String
	getAuthority()
	{
		return this.authority;
	}

	
	public
	void
	setAuthorities(
		final Collection<GrantedAuthority> authorities
	)
	{
		if ( null == authorities )
		{
			this.authority = null;
		}
		this.authority = CollectionUtils.concatenate( authorities, "," );
	}

	@JsonIgnore 
	public
	Collection<GrantedAuthority>
	getAuthorities()
	{
		if ( null == this.authority )
		{
			return null;
		}
		final ArrayList<GrantedAuthority> ret = new ArrayList<GrantedAuthority>();
		for ( final String auth : this.authority.split( "," ) )
		{
			ret.add( new SimpleGrantedAuthority( auth.trim() ) );
		}
		return ret;
	}
	

	public boolean isAccountNonExpired()
	{
		return true;
	}

	public boolean isAccountNonLocked()
	{
		return true;
	}

	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	public boolean isEnabled()
	{
		return true;
	}

	public int getTheNumberOfPoetries()
	{
		return this.nPoetries;
	}
	
	public void setTheNumberOfPoetries( final int nPoetries )
	{
		this.nPoetries = nPoetries;
	}

	public int getTheNumberOfClips()
	{
		return this.nClips;
	}
	
	public void setTheNumberOfClips( final int nClips )
	{
		this.nClips = nClips;
	}


	public int getTheNumberOfFollowings()
	{
		return this.nFollowings;
	}
	
	public void setTheNumberOfFollowings( final int nFollowings )
	{
		this.nFollowings = nFollowings;
	}


	public int getTheNumberOfFollowers()
	{
		return this.nFollowers;
	}
	
	public void setTheNumberOfFollowers( final int nFollowers )
	{
		this.nFollowers = nFollowers;
	}


	public String toString()
	{
		return "Poet[" + getUsername() + "]";
	}
	

}
