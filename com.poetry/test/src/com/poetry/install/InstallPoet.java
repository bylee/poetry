package com.poetry.install;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poetry.dao.BinaryDao;
import com.poetry.dao.PoetDao;
import com.poetry.model.Binary;
import com.poetry.model.Poet;

@Component
public class
InstallPoet
extends AbstractInstall
implements Install<Poet>
{
	@Autowired
	protected BinaryDao binaryDao;
	
	@Autowired
	protected PoetDao userDao;

	public Map<String, Poet> install() throws IOException
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
			String name = poet.getUsername() + ".jpg";
			byte[] icon = load( name );
			String mime = "image/jpg";
			if ( null == icon )
			{
				name = poet.getUsername() + ".png";
				icon = load( name );
				mime = "image/png";
			}
 
			final Binary poetImage = new Binary(
				name,
				poet.getUsername(),
				mime,
				icon
			);
			binaryDao.addNewBinary( poetImage );
			
			poet.setIcon( poetImage.getId() );
			userDao.addNewPoet( poet );
			ret.put( poet.getUsername(), poet );
		}

		return ret;
	}


}
