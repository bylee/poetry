package com.poetry.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.poetry.dao.ReplyDao;
import com.poetry.model.Reply;

public class
ReplyService
{
	@Autowired( required = false )
	protected PoetService poetService;
	
	@Autowired( required = false )
	protected ReplyDao replyDao;
	
	public
	void
	addReply(
		final Reply reply
	)
	{
		reply.setCreateDate( new Date() );
		replyDao.addReply( reply );
	}

	public
	List<Reply>
	list(
		final String targetId,
		final String start
	)
	{
		return replyDao.list( targetId );
	}

}
