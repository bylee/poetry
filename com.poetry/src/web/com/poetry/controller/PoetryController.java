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
import com.poetry.model.PoetryStatus;
import com.poetry.model.Reply;
import com.poetry.service.PoetService;
import com.poetry.service.PoetryService;
import com.poetry.service.ReplyService;
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

	@Autowired
	protected ReplyService replyService;
	
	/**
	 * 작성된 시를 저장한다.
	 * 
	 * 작성된 시의 공유 여부를 <code>action</code>를 통해 지정한다.
	 * 
	 * @param poetry 시 정보
	 * @param action 공유 여부
	 */
	@RequestMapping(
		value = "/poetry",
		method = POST
	)
	public void
	write(
		final Poetry poetry,
		final String action
	)
	{
		if ( !SignUtils.isSignIn() )
		{
			throw new IllegalArgumentException();
		}
		poetry.setAuthor( poetService.getPoetDetail( SignUtils.getSignedInUsername() ) );
		poetryService.add( poetry );
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
		return poetryService.getPoetry( poetryId );
	}

	/**
	 * 해당 <code>targetId</code>에 대한 {@link Reply}들을 반환한다.
	 * 
	 * @param targetId 대상에 대한 아이디
	 * @param startId 시작 아이디
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
		final String startId
	)
	{
		return replyService.list( targetId, startId );
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
		replyService.addReply( reply );
		return "success";
	}
	
	/**
	 * 시의 상태를 반환한다.
	 * 
	 * 시의 상태는 다음의 정보를 갖는다.
	 * <ul>
	 * 	<li>사용자가 이 시를 좋아하는지</li>
	 * 	<li>사용자가 이 시를 북마크( bookmark )했는지</li>
	 * 	<li>사용자가 이 시의 작가를 follow했는지</li>
	 * </ul>
	 * 
	 * @param poetryId 시의 아이디
	 * 
	 * @return 시의 상태 정보
	 */
	@RequestMapping(
		value = "/poetrystatus/{id}",
		method = GET
	)
	public
	@ResponseBody
	PoetryStatus
	getPoetryStatus(
		@PathVariable( "id" ) final String poetryId
	)
	{
		// star, bookmark, follow
		return poetryService.getPoetryStatus( poetryId );
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
		poetryService.addFollowing( poetId );
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
		poetryService.removeFollowing( poetId );
		return "success";
	}
	

}
