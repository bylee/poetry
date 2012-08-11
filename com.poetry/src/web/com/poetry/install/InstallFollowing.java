package com.poetry.install;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poetry.dao.BlockDao;
import com.poetry.dao.FollowingDao;
import com.poetry.model.Block;
import com.poetry.model.Following;

import escode.KeyedFactory;

@Component
public class
InstallFollowing
extends AbstractInstall
implements Install<Object>
{
	@Autowired
	protected KeyedFactory<Object, String> idGenerator;
	
	@Autowired
	protected FollowingDao followingDao;
	
	@Autowired
	protected BlockDao blockDao;

	@Override
	public
	Map<String, Object>
	install()
	throws Exception
	{
		final HashMap<String, Object> ret = new HashMap<String, Object>();
		
		final Object[] relations = new Object[] {
			new Following( "bylee", "anjong" ),
			new Following( "csoonoosc", "anjong" ),
			new Following( "csoonoosc", "bylee" ),
			new Following( "anjong", "bylee" ),
			new Following( "hanseoung82", "bylee" ),
			new Following( "anjong", "hellojintae" ),
			new Following( "hellojintae", "hanseoung82" ),
			new Following( "hellojintae", "anjong" ),
			new Following( "hanseoung82", "anjong" ),
			new Block( "bylee", "anjong" )
		};
		
		for ( final Object relation : relations )
		{
			if ( relation instanceof Following )
			{
				final Following following = (Following) relation;
				followingDao.addFollowing( following );
				ret.put( idGenerator.create( following ), relation );
			}
			else if ( relation instanceof Block )
			{
				final Block block = (Block) relation;
				blockDao.addBlock( block );
				ret.put( idGenerator.create( block ), relation );
			}
		}
		
		return ret;
	}

}
