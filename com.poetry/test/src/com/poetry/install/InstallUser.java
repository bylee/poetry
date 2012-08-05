package com.poetry.install;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poetry.dao.PoetDao;
import com.poetry.model.Poet;

@Component
public class
InstallUser
implements Install<Poet>
{
	@Autowired
	protected PoetDao userDao;

	public Map<String, Poet> install()
	{
		final HashMap<String, Poet> ret = new HashMap<String, Poet>();
		final Poet[] poets = new Poet[] {
			new Poet( "bylee", "Bon-Yong Lee", "bylee", "ROLE_ADMIN,ROLE_USER" ),
			new Poet( "anjong", "Jong-Hyun Kwon", "anjong" ),
			new Poet( "csoonoosc", "Choong-Soon Park", "csoonoosc" ),
			new Poet( "hellojintae", "Jin-Tae Jung", "hellojintae" ),
			new Poet( "hanseoung82", "Han-Seoung Sung", "hanseoung82" )
		};
		
		for ( final Poet poet : poets )
		{
			userDao.addNewPoet( poet );
			ret.put( poet.getUsername(), poet );
		}

		return ret;
	}


}
