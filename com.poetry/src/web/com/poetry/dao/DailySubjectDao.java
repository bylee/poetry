package com.poetry.dao;

import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Repository;

import com.poetry.model.DailySubject;

@Repository
public class
DailySubjectDao
extends AbstractDao
{
	/**
	 * 오늘의 시 주제를 반환한다. 오늘을 판단하는 기준은 Locale 상의 국가 정보를 기준으로 한다.
	 * 
	 * @param locale 사용자의 로케일
	 * 
	 * @return 오늘의 시
	 */
	public
	DailySubject
	getTodaySubject(
		final Locale locale
	)
	{
		Date date = new Date();
		return get( DailySubject.class, date );
	}
	
	/**
	 * 해당 지역의 <code>date</code> 날의 오늘의 시 정보를 반환한다.
	 * 
	 * @param locale 지역정보
	 * @param date 날짜
	 * 
	 * @return 오늘의 시 정보
	 */
	public
	DailySubject
	getTodaySubject(
		final Locale locale,
		final Date date
	)
	{
		return get( DailySubject.class, date );
	}

}
