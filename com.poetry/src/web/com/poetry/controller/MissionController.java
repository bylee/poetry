package com.poetry.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetry.model.Mission;
import com.poetry.service.BinaryService;

@Controller
public class
MissionController
extends AbstractController
{
	@Autowired
	protected BinaryService binaryService;
	
	@InitBinder
	protected void initBinder(
		final HttpServletRequest request,
		final ServletRequestDataBinder binder
	)
	throws Exception
	{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor( dateFormat, true );
        binder.registerCustomEditor( Date.class, editor );
      }

	
	@RequestMapping(
		value = "/mission",
		method = POST
	)
	public
	@ResponseBody
	String
	uploadMission(
		final Mission mission
	)
	{
		logger.trace( "trying upload :{}", mission );
		binaryService.upload( mission );
		return "success";
	}
	
	
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
		value = { "/mission/{date}" },
		method = GET
	)
	public
	@ResponseBody
	Mission
	getMission(
		@PathVariable( "date" ) final Date date
	)
	{
		logger.trace( "trying get {}'s mission", date );
		return binaryService.getMission( date );
	}

}