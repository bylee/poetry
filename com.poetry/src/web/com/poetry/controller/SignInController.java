package com.poetry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class
SignInController
{
	@RequestMapping( "/service/signin" )
	public
	String
	formSignin()
	{
		return "signin";
	}

}
