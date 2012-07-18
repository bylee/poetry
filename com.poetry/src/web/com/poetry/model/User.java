package com.poetry.model;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table( name = "Poet")
public class User
implements UserDetails
{
	private static final long serialVersionUID = 7403546217867181618L;
	
	protected static Collection<GrantedAuthority> USER_AUTHORITIES = 
		Arrays.<GrantedAuthority>asList( new SimpleGrantedAuthority( "ROLE_USER" ) );
	
	@Id
	protected String username;
	
	protected String password;
	
	@Column( name = "pen_name" )
	protected String penName;
	
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

	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return USER_AUTHORITIES;
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

	public String toString()
	{
		return "Poet[" + getUsername() + "]";
	}
	

}
