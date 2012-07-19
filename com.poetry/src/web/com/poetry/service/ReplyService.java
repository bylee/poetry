package com.poetry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.poetry.dao.ReplyDao;
import com.poetry.model.Reply;

public class
ReplyService
{
	@Autowired
	protected PoetService poetService;
	
	@Autowired
	protected ReplyDao replyDao;
	
	public
	void addReply(
		final Reply reply
	)
	{
		replyDao.insert( reply );
	}

	public
	Reply
	get(
		final String id
	)
	{
		return replyDao.get( id );
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
