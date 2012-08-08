package com.poetry.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


public class AbstractController
{
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

	

	protected final Logger logger = LoggerFactory.getLogger( getClass() );

}
