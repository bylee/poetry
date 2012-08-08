package com.poetry.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetry.model.Poet;
import com.poetry.model.Poetry;
import com.poetry.service.PoetService;
import com.poetry.service.PoetryService;

@Controller
public class
MyPageController
extends AbstractController
{
	@Autowired
	protected PoetService poetService;
	
	@Autowired
	protected PoetryService poetryService;
	
	@RequestMapping(
		value = "/poet/{username}",
		method = GET
	)
	public
	@ResponseBody
	Poet
	getPoetDetail(
		@PathVariable( "username" ) final String username
	)
	{
		return poetService.getPoetDetail( username );
	}

	@RequestMapping(
		value = "/poet/{username}/poetry",
		method = GET
	)
	public
	@ResponseBody
	List<Poetry>
	getMyPoetries(
		@PathVariable( "username" ) final String poetId,
		@RequestParam( value = "start", required = false ) final String start
	)
	{
		return poetryService.getPoetiesOf( poetId, start );
		
	}

	
	@RequestMapping(
		value = "/poet/{username}/bookmark",
		method = GET
	)
	public
	@ResponseBody
	List<Poetry>
	getMyBookmarks(
		@PathVariable( "username" ) final String poetId,
		@RequestParam( value = "start", required = false ) final String start
	)
	{
		return poetryService.getBookmarksOf( poetId, start );
		
	}

	@RequestMapping(
		value = "/poet/{username}/following",
		method = GET
	)
	public
	@ResponseBody
	List<Poet>
	getFollowing(
		@PathVariable( "username" ) final String poetId
	)
	{
		return poetService.getFollowings( poetId );
	}

	@RequestMapping(
		value = "/poet/{username}/follower",
		method = GET
	)
	public
	@ResponseBody
	List<Poet>
	getFollower(
		@PathVariable( "username" ) final String poetId
	)
	{
		return poetService.getFollowers( poetId );
	}

	/**
	 * 시인을 팔로우한다.
	 * 
	 * @param poetId 시인의 아이디
	 */
	@RequestMapping(
		value = "/following/{poetId}",
		method = POST
	)
	public
	@ResponseBody
	String
	addFollowing(
		@PathVariable( "poetId" )
		final String poetId
	)
	{
		poetService.addFollowing( poetId );
		return "success";
	}
	
	/**
	 * 시인을 팔로우하지 않는다.
	 * 
	 * @param poetId 시인 아이디
	 */
	@RequestMapping(
		value = "/following/{poetId}",
		method = DELETE
	)
	public
	@ResponseBody
	String
	deleteFollowing(
		@PathVariable( "poetId" )
		final String poetId
	)
	{
		poetService.removeFollowing( poetId );
		return "success";
	}

	@RequestMapping(
		value = "/poet/{poetId}/block",
		method = GET
	)
	public
	@ResponseBody
	List<Poet>
	getBlocks(
		@PathVariable( "poetId" )
		final String poetId
	)
	{
		return poetService.getBlocks( poetId );
	}

	@RequestMapping(
		value = "/block/{poetId}",
		method = POST
	)
	public
	@ResponseBody
	String
	addBlock(
		@PathVariable( "poetId" )
		final String poetId
	)
	{
		poetService.addBlock( poetId );
		return "success";
	}
	
	@RequestMapping(
		value = "/block/{poetId}",
		method = DELETE
	)
	public
	@ResponseBody
	String
	deleteBlock(
		@PathVariable( "poetId" )
		final String poetId
	)
	{
		poetService.removeBlock( poetId );
		return "success";
	}


}
