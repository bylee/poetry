package com.poetry.install;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poetry.dao.FollowingDao;
import com.poetry.model.Following;

import escode.KeyedFactory;

@Component
public class
InstallFollowing
extends AbstractInstall
implements Install<Following>
{
	@Autowired
	protected KeyedFactory<Object, String> idGenerator;
	
	@Autowired
	protected FollowingDao followingDao;

	@Override
	public
	Map<String, Following>
	install()
	throws Exception
	{
		final HashMap<String, Following> ret = new HashMap<String, Following>();
		
		final Following[] followings = new Following[] {
			new Following( "bylee", "anjong" ),
			new Following( "csoonoosc", "anjong" ),
			new Following( "csoonoosc", "bylee" ),
			new Following( "anjong", "bylee" ),
			new Following( "hanseoung82", "bylee" ),
			new Following( "anjong", "hellojintae" ),
			new Following( "hellojintae", "hanseoung82" ),
			new Following( "hellojintae", "anjong" ),
			new Following( "hanseoung82", "anjong" ),
		};
		
		for ( final Following following : followings )
		{
			followingDao.addFollowing( following );
			ret.put( idGenerator.create( following ), following );
		}
		
		return ret;
	}

}
