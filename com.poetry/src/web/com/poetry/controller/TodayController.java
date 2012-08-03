package com.poetry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetry.model.Poetry;
import com.poetry.service.PoetryService;

@Controller
public class TodayController
extends AbstractController
{
	@Autowired
	protected PoetryService poetryService;
	
	/**
	 * 오늘의 시들을 반환한다.
	 * 
	 * 오늘의 시를 모두 반환하는 것이 아니라 나누어 전송한다.
	 * 
	 * <code>startId</code>가 <code>null</code>이면, 첫번째를 전송한다.
	 * 
	 * @param date 시작할 시의 id
	 * 
	 * @return 시 {@link List}
	 */
	@RequestMapping(
		value = { "/today" , "/today/{date}" },
		method = RequestMethod.GET
	)
	public
	@ResponseBody
	List<Poetry>
	getTodayPoetry(
		final String date
	)
	{
		return poetryService.getTodayPoetries();
	}
	

}
