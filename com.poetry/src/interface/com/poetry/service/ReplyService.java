package com.poetry.service;

import java.util.List;

import com.poetry.model.Reply;

public interface
ReplyService
{

	void addReply( Reply reply );

	Reply get( String id );

	List<Reply> list( String targetId, String start );

}
