package com.poetry.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.poetry.dao.ReplyDao;
import com.poetry.model.Reply;

public class
ReplyServiceImpl
implements ReplyService
{
	@Autowired
	protected PoetService poetService;
	
	@Autowired
	protected ReplyDao replyDao;
	
	@Override
	public
	void
	addReply(
		final Reply reply
	)
	{
		reply.setCreateDate( new Date() );
		replyDao.addReply( reply );
	}

	@Override
	public
	Reply
	get(
		final String id
	)
	{
		return replyDao.get( id );
	}

	@Override
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
