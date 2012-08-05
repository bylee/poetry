package com.poetry.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetry.model.Poetry;
import com.poetry.service.PoetryService;
import com.poetry.util.SignUtils;

import escode.util.Assert;

@Controller
public class
NewsFeedController
{
	@Autowired
	protected PoetryService poetryService;
	
	@RequestMapping( value = "/newsfeed", method = GET )
	public
	@ResponseBody
	List<Poetry>
	getPoetries(
		@RequestParam( value = "start", required = false )
		final String start
	)
	{
		Assert.isTrue( SignUtils.isSignIn() );
		final String username = SignUtils.getSignedInUsername();
		
		return poetryService.getNewsfeed( username, start );
	}

}
