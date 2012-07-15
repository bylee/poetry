package com.poetry.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class
PoetryDetail
extends Poetry
{
	protected HashSet<String> tags = new HashSet<String>();
	
	public Collection<String> getTags()
	{
		return Collections.unmodifiableCollection( this.tags );
	}
	
	public void setTags(
		final Collection<String> tags
	)
	{
		this.tags = new HashSet<String>( tags );
	}
	
	

}
