package com.poetry.controller;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetry.model.Binary;
import com.poetry.model.Poetry;
import com.poetry.model.Reply;
import com.poetry.service.BinaryService;
import com.poetry.service.PoetryService;
import com.poetry.service.ReplyService;

@Controller
public class
HomeController
{
	@Autowired
	protected PoetryService poetryService;
	
	@Autowired
	protected BinaryService binaryService;
	
	@Autowired
	protected ReplyService replyService;
	
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
		value = "/today/{date}",
		method = RequestMethod.GET
	)
	public
	@ResponseBody
	List<Poetry>
	getTodayPoetry(
		final String date
	)
	{
		return poetryService.getTodayPoetries( date );
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
		@PathVariable( "id" ) final String id
	)
	{
		return poetryService.getPoetry( id );
	}
	
	@RequestMapping(
		value = "/binary",
		method = RequestMethod.POST
	)
	public
	void
	uploadBinary(
		InputStream in
	)
	{
		final Binary binary = new Binary();
		binaryService.upload( binary );
	}
	
	
	/**
	 * 아이디가 <code>id</code>인 저장된 이미지를 반환한다.
	 * 
	 * @param id 이미지 아이디
	 * 
	 * @return 이미지의 byte[]
	 */
	@RequestMapping(
		value = "/binary/{id}",
		method = RequestMethod.GET
	)
	public
	ResponseEntity<byte[]>
	getBinary(
		@PathVariable( "id" ) final String id
	)
	{
		final Binary binary = binaryService.getBinary( id );
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType( MediaType.parseMediaType( binary.getMime() ) );
		return new ResponseEntity<byte[]>( binary.getContents(), headers, HttpStatus.CREATED );
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
		value = "/reply/{targetid}",
		method = RequestMethod.GET
	)
	public
	@ResponseBody
	List<Reply>
	getReply(
		@PathVariable( "targetid" )
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
	
	

}
