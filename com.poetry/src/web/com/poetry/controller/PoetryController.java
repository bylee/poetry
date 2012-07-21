package com.poetry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetry.model.Poetry;
import com.poetry.model.Reply;
import com.poetry.service.PoetryService;
import com.poetry.service.ReplyService;

@Controller
public class PoetryController
{
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
		method = RequestMethod.POST
	)
	public void
	write(
		final Poetry poetry,
		final String action
	)
	{
		poetryService.add( poetry );
	}
	
	/**
	 * 지정한 시 정보를 반환한다.
	 * 
	 * @param id 가져올 시의 id
	 * 
	 * @return id가 <code>id</code>인 시
	 */
	@RequestMapping(
		value = "/poetry/{id}",
		method = RequestMethod.GET
	)
	public
	@ResponseBody
	Poetry
	getPoetry(
		@PathVariable( "id" )
		final String id
	)
	{
		return poetryService.getPoetry( id );
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
		method = RequestMethod.GET
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
		method = RequestMethod.POST
	)
	public
	void
	reply(
		final Reply reply
	)
	{
		replyService.addReply( reply );
	}
	
	@RequestMapping(
		value = "/star/{poetryId}",
		method = RequestMethod.POST
	)
	public
	@ResponseBody
	void
	addStar(
		@PathVariable( "poetryId" )
		final String poetryId
	)
	{
		poetryService.addStar( poetryId );
	}
	
	@RequestMapping(
		value = "/star/{poetryId}",
		method = RequestMethod.DELETE
	)
	public
	@ResponseBody
	void
	deleteStar(
		@PathVariable( "poetryId" )
		final String poetryId
	)
	{
		poetryService.removeStar( poetryId );
	}
	
	

}
