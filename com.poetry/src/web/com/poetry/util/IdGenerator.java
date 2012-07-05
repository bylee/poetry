package com.poetry.util;

import escode.KeyedFactory;
import escode.util.ObjectUtils;

public class
IdGenerator
implements KeyedFactory<Object, String>
{

	/* (non-Javadoc)
	 * @see escode.KeyedFactory#create(java.lang.Object)
	 */
	public
	String
	create(
		final Object key
	)
	{
		return ObjectUtils.generateGUID( key );
	}

}
