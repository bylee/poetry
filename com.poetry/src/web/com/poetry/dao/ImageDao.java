package com.poetry.dao;

import org.springframework.stereotype.Repository;

import com.poetry.model.ImageAnalysis;

@Repository
public class
ImageDao
extends AbstractDao
{
	public void addNewImageAnalysis( ImageAnalysis imageAnalysis )
	{
		insert( imageAnalysis );
	}

}
