package com.poetry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetry.model.Poet;
import com.poetry.service.PoetService;

@Controller
public class
MyPageController
{
	@Autowired
	protected PoetService poetService;
	
	@RequestMapping( "/poet/{username}" )
	public
	@ResponseBody
	Poet
	getPoetDetail(
		@PathVariable( "username" ) final String username
	)
	{
		return poetService.getPoetDetail( username );
	}

}
