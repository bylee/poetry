package com.poetry.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetry.model.Poetry;

@Controller
public class
NewsFeedController
{
	@RequestMapping( value = "/newsfeed", method = GET )
	public
	@ResponseBody
	List<Poetry>
	getPoetries()
	{
		return null;
	}

}
