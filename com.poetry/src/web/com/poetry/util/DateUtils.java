package com.poetry.util;

import java.util.Date;

public class DateUtils {

	public static Date getYesterday()
	{
		return new Date( System.currentTimeMillis() + 24 * 60 * 60 * 1000 );
	}
	public static Date getTomorrow()
	{
		return new Date( System.currentTimeMillis() + 24 * 60 * 60 * 1000 );
	}
}
