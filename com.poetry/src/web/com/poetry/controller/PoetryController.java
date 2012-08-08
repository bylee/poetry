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

import com.poetry.model.Poetry;
import com.poetry.model.Reply;
import com.poetry.service.PoetService;
import com.poetry.service.PoetryService;
import com.poetry.util.SignUtils;

@Controller
public class
PoetryController
extends AbstractController
{
	@Autowired
	protected PoetService poetService;
	
	@Autowired
	protected PoetryService poetryService;

	/**
	 * 작성된 시를 저장한다.
	 * 
	 * 작성된 시의 공유 여부를 <code>action</code>를 통해 지정한다.
	 * 
	 * @param poetry 시 정보
	 * @param action 시를 쓰는 장소
	 */
	@RequestMapping(
		value = "/poetry",
		method = POST
	)
	public
	@ResponseBody
	String
	write(
		final Poetry poetry,
		@RequestParam( value = "where", required = false ) final String action
	)
	{
		if ( !SignUtils.isSignIn() )
		{
			throw new IllegalArgumentException();
		}
		poetry.setAuthor( poetService.getPoetDetail( SignUtils.getSignedInUsername() ) );
		if ( "mission".equals( action ) )
		{
			poetryService.addMissionPoetry( poetry );
		}
		else
		{
			poetryService.addNewPoetry	( poetry );
		}
		
		return "success";
		
	}

	/**
	 * 지정한 시 정보를 반환한다.
	 * 
	 * @param poetryId 가져올 시의 id
	 * 
	 * @return id가 <code>id</code>인 시
	 */
	@RequestMapping(
		value = "/poetry/{id}",
		method = GET
	)
	public
	@ResponseBody
	Poetry
	getPoetry(
		@PathVariable( "id" )
		final String poetryId
	)
	{
		String username = SignUtils.getSignedInUsername();
		return poetryService.getPoetry( poetryId, username );
	}

	/**
	 * 해당 <code>targetId</code>에 대한 {@link Reply}들을 반환한다.
	 * 
	 * @param targetId 대상에 대한 아이디
	 * @param start 시작 아이디
	 * 
	 * @return {@link Reply}의 {@link List}
	 */
	@RequestMapping(
		value = "/reply/{targetId}",
		method = GET
	)
	public
	@ResponseBody
	List<Reply>
	getReply(
		@PathVariable( "targetId" )
		final String targetId,
		@RequestParam( value = "start", required = false )
		final String start
	)
	{
		return poetryService.list( targetId, start );
	}
	
	/**
	 * 대상에 대한 {@link Reply}를 추가한다.
	 * 
	 * @param reply 추가할 {@link Reply}
	 */
	@RequestMapping(
		value = "/reply",
		method = POST
	)
	public
	@ResponseBody
	String
	reply(
		final Reply reply
	)
	{
		reply.setWriter( poetService.getPoetDetail( SignUtils.getSignedInUsername() ) );
		poetryService.addReply( reply );
		return "success";
	}
	
	/**
	 * 시에 좋아한다는 표시를 남긴다.
	 * 
	 * @param poetryId 시의 아이디
	 */
	@RequestMapping(
		value = "/star/{poetryId}",
		method = POST
	)
	public
	@ResponseBody
	String
	addStar(
		@PathVariable( "poetryId" )
		final String poetryId
	)
	{
		poetryService.addStar( poetryId );
		return "success";
	}
	
	/**
	 * 시를 좋아한다는 마크를 지운다.
	 * 
	 * @param poetryId 시의 아이디
	 */
	@RequestMapping(
		value = "/star/{poetryId}",
		method = DELETE
	)
	public
	@ResponseBody
	String
	deleteStar(
		@PathVariable( "poetryId" )
		final String poetryId
	)
	{
		poetryService.removeStar( poetryId );
		return "success";
	}
	
	/**
	 * 시에 바로가기 마크를 단다
	 * 
	 * @param poetryId 시의 아이디
	 */
	@RequestMapping(
		value = "/bookmark/{poetryId}",
		method = POST
	)
	public
	@ResponseBody
	String
	addBookmark(
		@PathVariable( "poetryId" )
		final String poetryId
	)
	{
		poetryService.addBookmark( poetryId );
		return "success";
	}
	
	/**
	 * 시에 바로가기 마클를 제거한다.
	 * 
	 * @param poetryId 시의 아이디
	 */
	@RequestMapping(
		value = "/bookmark/{poetryId}",
		method = DELETE
	)
	public
	@ResponseBody
	String
	deleteBookmark(
		@PathVariable( "poetryId" )
		final String poetryId
	)
	{
		poetryService.removeBookmark( poetryId );
		return "success";
	}
	
}
