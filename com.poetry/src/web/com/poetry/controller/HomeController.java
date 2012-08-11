package com.poetry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.poetry.install.InstallAll;
import com.poetry.model.Binary;
import com.poetry.service.MissionService;

import escode.util.CollectionUtils;

@Controller
public class
HomeController
extends AbstractController
{
	@Autowired( required = false )
	protected InstallAll install;
	
	@Autowired
	protected MissionService binaryService;
	
	@RequestMapping()
	public
	@ResponseBody
	String
	install() throws Exception
	{
		if ( null == install )
		{
			return "fail";
		}
		
		install.execute();
		return "success";
	}
	
	@RequestMapping(
		value = "/binary",
		method = RequestMethod.POST
	)
	@ResponseBody
	public
	Binary
	uploadBinary(
		final Binary binary,
		final BindingResult result
	)
	{
		final CommonsMultipartFile file = binary.getUploadFile();
		if ( null == file )
		{
			return null;
		}
		if ( file.isEmpty() )
		{
			logger.warn( "file is empty" );
			return null;
		}
		binary.setName( file.getOriginalFilename() );
		binary.setMime( file.getContentType() );
		binary.setContents( file.getBytes() );
		
		logger.info(
			"File uploaded :{}[{}]",
			binary.getName(),
			CollectionUtils.size( binary.getContents() )
		);
		binaryService.upload( binary );
		return binary;
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
		if ( null == binary )
		{
			return new ResponseEntity<byte[]>( new byte[] {}, headers, HttpStatus.OK );
		}
		
		headers.setContentType( MediaType.parseMediaType( binary.getMime() ) );
		return new ResponseEntity<byte[]>( binary.getContents(), headers, HttpStatus.OK );
	}
	
}
