package com.poetry.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SignUtils
{
	public static boolean isSignIn()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return ( null != auth && auth.isAuthenticated() );
	}
	
	public static String getSignedInUsername()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ( null != auth && auth.isAuthenticated() )
		{
			return auth.getName();
		}
		else
		{
			return null;
		}
			

	}

}
