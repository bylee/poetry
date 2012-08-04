package com.poetry.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetry.model.Poet;
import com.poetry.model.Poetry;
import com.poetry.service.PoetService;
import com.poetry.service.PoetryService;
import com.poetry.util.SignUtils;

@Controller
public class
MyPageController
extends AbstractController
{
	@Autowired
	protected PoetService poetService;
	
	@Autowired
	protected PoetryService poetryService;
	
	@RequestMapping( value = "/poet/{username}", method = GET )
	public
	@ResponseBody
	Poet
	getPoetDetail(
		@PathVariable( "username" ) final String username
	)
	{
		return poetService.getPoetDetail( username );
	}

	@RequestMapping( value = "/poet/{username}/poetry", method = GET )
	public
	@ResponseBody
	List<Poetry>
	getMyPoetries(
		@PathVariable( "username" ) final String poetId
	)
	{
		return poetryService.getPoetiesOf( poetId );
		
	}

	
	@RequestMapping( value = "/poet/{username}/bookmark", method = GET )
	public
	@ResponseBody
	List<Poetry>
	getMyBookmarks(
		@PathVariable( "username" ) final String poetId
	)
	{
		return poetryService.getBookmarksOf( poetId );
		
	}

	@SuppressWarnings("unchecked")
	@RequestMapping( value = "/poet/{username}/following", method = GET )
	public
	@ResponseBody
	List<Poet>
	getFollowing()
	{
		if ( SignUtils.isSignIn() )
		{
			return Collections.EMPTY_LIST;
		}
		
//		return poetryService.getPoetiesOf( SignUtils.getSignedInUsername() );
		return null;

		
	}

	@SuppressWarnings("unchecked")
	@RequestMapping( value = "/poet/{username}/follower", method = GET )
	public
	@ResponseBody
	List<Poet>
	getFollower()
	{
		if ( SignUtils.isSignIn() )
		{
			return Collections.EMPTY_LIST;
		}
		
//		return poetryService.getPoetiesOf( SignUtils.getSignedInUsername() );
		return null;
		
	}

}
