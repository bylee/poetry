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
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetry.model.Binary;
import com.poetry.model.Poetry;
import com.poetry.service.BinaryService;
import com.poetry.service.PoetryService;

@Controller
public class
HomeController
{
	@Autowired
	protected PoetryService poetryService;
	
	@Autowired
	protected BinaryService binaryService;
	
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
	
}
